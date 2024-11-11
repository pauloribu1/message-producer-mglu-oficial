package com.ribu.message_producer.repository;

import com.ribu.message_producer.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
