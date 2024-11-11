package com.ribu.message_producer.service;

import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;


    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void scheduleMessage(ScheduleMessageDTO dto){
        messageRepository.save(dto.toMessage());

    }

}
