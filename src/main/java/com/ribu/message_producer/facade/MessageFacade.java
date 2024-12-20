package com.ribu.message_producer.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.producer.MessageRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageFacade {
    @Autowired
    private MessageRequestProducer producer;

    public String messageQueue(ScheduleMessageDTO dto) {
        try {
            producer.sendToRabbit(dto);
        } catch (JsonProcessingException e) {
            return "ERROR! While processing the message Queue" + e.getMessage();
        }
        return "The communication to "+ dto.getDestination() +" was booked. We will send the message in the time requested.";

    }
}
