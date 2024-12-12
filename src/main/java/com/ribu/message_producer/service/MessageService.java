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

    public Long scheduleMessage(ScheduleMessageDTO dto){
        Message message = messageRepository.save(dto.toMessage());
        System.out.println(message.getMessageId());
        return message.getMessageId();
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

    public void markAsSent(Long id){
        Optional<Message> message = messageRepository.findById(id);
        Message mes = message.get();

        if (mes.getStatus().getDescription().toUpperCase().equals(Status.Options.PENDING.toString())) {
            mes.setStatus(Status.Options.SUCESS.toStatus());
            messageRepository.save(mes);
        }
    }

}
