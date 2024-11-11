package com.ribu.message_producer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ribu.message_producer.entity.Channel;
import com.ribu.message_producer.entity.Message;
import com.ribu.message_producer.entity.Status;

import java.time.LocalDateTime;


public record ScheduleMessageDTO(String content,
         String dateTime,
         String destination,
         Channel.Options channel){




    public Message toMessage() {
        return new Message(
                dateTime,
                destination,
                content,
                channel.toChannel(),
                Status.Options.PENDING.toStatus()
        );
    }
}
