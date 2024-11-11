package com.ribu.message_producer.controller;


import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.entity.Message;
import com.ribu.message_producer.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> scheduleMessage(@RequestBody ScheduleMessageDTO dto){
        messageService.scheduleMessage(dto);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") Long id) {
        System.out.println("Buscando mensagem com ID: " + id);
        var message = messageService.findById(id);
        System.out.println("Mensagem encontrada: " + message);

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
