package com.ribu.message_producer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.service.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message; // atenção aqui o message é a biblioteca
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestConsumer {
    @Autowired private MessageService messageService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @RabbitListener(queues = { "message-queue"})
    public void receiveMessage (@Payload Message<String> message){
        try {
            String payload = message.getPayload();
            ScheduleMessageDTO dto = objectMapper.readValue(payload, ScheduleMessageDTO.class);

            System.out.println("----------NEW MESSAGE INCOMING: ------------" + dto);

            Long messageId = dto.getMessageId();
            if (messageId != null) {
                messageService.markAsSent(messageId);
            } else {
                System.out.println("---------MESSAGE ID NOT found!---------");
            }
        } catch (Exception e) {
            System.err.println(" --------- Error consuming message: " + e.getMessage());
        }

    }
}
