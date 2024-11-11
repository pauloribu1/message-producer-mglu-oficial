package com.ribu.message_producer.scheduler;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

public class ConsumerTaskScheduler {


    @Scheduled(fixedDelay = 60000)
    public void checkTasks() {
        var dateTime = LocalDateTime.now();
        System.out.println("Agora são " + dateTime);

    }
}
