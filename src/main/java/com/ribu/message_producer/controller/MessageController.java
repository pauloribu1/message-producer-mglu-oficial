package com.ribu.message_producer.controller;


import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.entity.Channel;
import com.ribu.message_producer.entity.Message;
import com.ribu.message_producer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ribu.message_producer.facade.MessageFacade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired private MessageFacade messageFacade;
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> scheduleMessage(@RequestBody ScheduleMessageDTO dto){
        if (!isValidDateTime(dto.getDateTime())) {
            return ResponseEntity.badRequest().body("Formato de data e hora inválido. Use o formato: yyyy-MM-dd'T'HH:mm:ss");
        }

        if (!isValidChannel(dto.getChannel().toChannel())) {
            return ResponseEntity.badRequest().body("Canal inválido. Envie : " + getValidChannels());
        }



        var messageId = messageService.scheduleMessage(dto);
        ScheduleMessageDTO newDto = new ScheduleMessageDTO(
                messageId,
                dto.getContent(),
                dto.getDateTime(),
                dto.getDestination(),
                dto.getChannel()
        );

        var response = messageFacade.messageQueue(newDto);

        return ResponseEntity.accepted().body(response);
    }

    private boolean isValidChannel(Channel channel) {
        if (channel == null || channel.getContent() == null) {
            return false;
        }
        try {
            Channel.Options.valueOf(channel.getContent().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private String getValidChannels() {
        return String.join(", ", Arrays.stream(Channel.Options.values())
                .map(Enum::name)
                .toArray(String[]::new));
    }
    private boolean isValidDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") Long id) {
        System.out.println("Buscando mensagem com ID: " + id);
        var message = messageService.findById(id);

        if (message.isPresent()) {
            Message foundMessage = message.get();
            System.out.println("Mensagem encontrada");
            System.out.println("Conteúdo da mensagem: " + foundMessage.getContent());
            System.out.println("Destino: " + foundMessage.getDestination());
            System.out.println("Data e Hora: " + foundMessage.getDateTime());
            System.out.println("Canal: " + foundMessage.getChannel());
            System.out.println("Status: " + foundMessage.getStatus());
        } else {
            System.out.println("Mensagem não encontrada.");
        }

        if (message.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message.get());
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelMessage(@PathVariable Long id){
        messageService.cancelMessage(id);
        return ResponseEntity.noContent().build();

    }
}
