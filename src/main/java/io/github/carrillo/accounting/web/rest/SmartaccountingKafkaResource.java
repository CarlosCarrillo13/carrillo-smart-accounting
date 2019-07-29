package io.github.carrillo.accounting.web.rest;

import io.github.carrillo.accounting.service.SmartaccountingKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/smartaccounting-kafka")
public class SmartaccountingKafkaResource {

    private final Logger log = LoggerFactory.getLogger(SmartaccountingKafkaResource.class);

    private SmartaccountingKafkaProducer kafkaProducer;

    public SmartaccountingKafkaResource(SmartaccountingKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
