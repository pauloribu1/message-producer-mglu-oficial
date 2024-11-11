package com.ribu.message_producer.config;

import com.ribu.message_producer.entity.Channel;
import com.ribu.message_producer.entity.Status;
import com.ribu.message_producer.repository.ChannelRepository;
import com.ribu.message_producer.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DbInitializer implements CommandLineRunner {

    private final ChannelRepository channelRepository;

    private final StatusRepository statusRepository;

    public DbInitializer(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.Options.values())
                .map(Channel.Options::toChannel)
                .forEach(channelRepository::save);

        Arrays.stream(Status.Options.values())
                .map(Status.Options::toStatus)
                .forEach(statusRepository::save);
    }
}
