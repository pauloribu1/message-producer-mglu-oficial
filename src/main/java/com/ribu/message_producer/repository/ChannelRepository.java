package com.ribu.message_producer.repository;

import com.ribu.message_producer.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    
}
