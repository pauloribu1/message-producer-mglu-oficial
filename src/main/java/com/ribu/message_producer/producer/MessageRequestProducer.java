package com.ribu.message_producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ribu.message_producer.dto.ScheduleMessageDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendToRabbit (ScheduleMessageDTO dto) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "message-exchange",
                "message-rout-key",
                        objectMapper.writeValueAsString(dto));



    }
}
