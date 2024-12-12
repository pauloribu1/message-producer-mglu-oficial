package com.ribu.message_producer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.service.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message; // atenção que message é a biblioteca ñ objeto
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Long messageId = dto.getMessageId();
            String dateTimeString = dto.getDateTime();
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
            LocalDateTime now = LocalDateTime.now();

            if (messageId != null && dateTime.isAfter(now)) {
                messageService.markAsSent(messageId);
            } else {
                //maybe place a logic here that send back the message to the queue
                messageService.scheduleMessage(dto);
                System.out.println("---------MESSAGE ID NOT found! Something went wrong ---------");
            }
        } catch (Exception e) {
            System.err.println(" --------- Error consuming message: " + e.getMessage());
        }

    }
}
