package com.ribu.message_producer.controller;


import com.ribu.message_producer.dto.ScheduleMessageDTO;
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

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Funcionando");
    }
}
