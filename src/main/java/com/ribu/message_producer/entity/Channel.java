package com.ribu.message_producer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_channel")
public class Channel {

    @Id
    private Long channelId;

    private String content;

    public enum Options{
        EMAIL(1L, "email"),
        SMS(2L, "sms"),
        PUSH(3L,"push"),
        WHATSAPP(4L, "whatsapp");

        private Long id;
        private String content;

        Options(Long id, String content){
            this.id = id;
            this.content = content;
        }

        public Channel toChannel(){
            return new Channel(id, content);
        }
    }
}
