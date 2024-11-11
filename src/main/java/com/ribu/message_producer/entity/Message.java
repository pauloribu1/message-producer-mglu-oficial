package com.ribu.message_producer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private LocalDateTime dateTime;

    private String destination;

    private String content;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Message(String content) {
        this.content = content;
    }

    public Message(LocalDateTime dateTime, String destination, String content, Channel channel, Status status) {
        this.dateTime = dateTime;
        this.destination = destination;
        this.content = content;
        this.channel = channel;
        this.status = status;
    }
}
