package io.github.carrillo.accounting.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmartaccountingKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(SmartaccountingKafkaConsumer.class);
    private static final String TOPIC = "topic_smartaccounting";

    @KafkaListener(topics = "topic_smartaccounting", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
