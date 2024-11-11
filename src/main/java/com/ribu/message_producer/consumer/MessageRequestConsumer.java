package com.ribu.message_producer.consumer;

import com.ribu.message_producer.service.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message; // atenção aqui o message é a biblioteca
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestConsumer {
    @Autowired private MessageService messageService;
    @RabbitListener(queues = { "message-queue"})
    public void receiveMessage (@Payload Message message){
        System.out.println("Processando agendamento" + message);
        messageService.markAsSent();

    }
}
