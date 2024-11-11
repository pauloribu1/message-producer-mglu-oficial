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
@Table(name = "tb_status")
public class Status {
    @Id
    private Long statusId;

    private String description;

    public enum Options{
        PENDING(1L,"pending"),

        SUCESS(2L, "sucess"),
        ERROR(3L, "error"),
        CANCELED(4L, "canceled"),
        READY(5L, "ready");

        private Long id;
        private String content;

        Options(Long id,String content){
            this.id = id;
            this.content = content;
        }

        public Status toStatus() {
            return new Status(id, content);
        }

    }
}
