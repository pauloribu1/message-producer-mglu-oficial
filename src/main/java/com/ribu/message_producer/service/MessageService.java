package com.ribu.message_producer.service;

import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.entity.Message;
import com.ribu.message_producer.entity.Status;
import com.ribu.message_producer.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;


    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void scheduleMessage(ScheduleMessageDTO dto){
        messageRepository.save(dto.toMessage());

        // TO DO RABBIT MQ

    }

    public Optional<Message> findById(Long id){
        return messageRepository.findById(id);

    }

    public void cancelMessage(Long id) {
        var message = findById(id);

        if( message.isPresent()){
            message.get().setStatus(Status.Options.CANCELED.toStatus());
            messageRepository.save(message.get());
        }
    }

}
