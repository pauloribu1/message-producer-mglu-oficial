package com.ribu.message_producer.dto;

import com.ribu.message_producer.entity.Channel;
import com.ribu.message_producer.entity.Message;
import com.ribu.message_producer.entity.Status;


public record ScheduleMessageDTO(Long messageId, String content,
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

    public String getDateTime() {
        return dateTime;
    }

    public Channel.Options getChannel() {
        return channel;
    }

    public String getDestination() {
        return destination;
    }

    public String getContent() {
        return content;
    }

    public Long getMessageId() {
        return messageId;
    }
}
