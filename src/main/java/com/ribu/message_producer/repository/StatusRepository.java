package com.ribu.message_producer.repository;

import com.ribu.message_producer.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
