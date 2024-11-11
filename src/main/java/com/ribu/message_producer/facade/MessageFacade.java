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
            return "Ocorreu um erro ao solicitar agendamento" + e.getMessage();
        }
        return "Agendamento realizado com sucesso. Muito obrigado.";

    }
}
