package io.github.carrillo.accounting.web.rest;

import io.github.carrillo.accounting.SmartaccountingApp;
import io.github.carrillo.accounting.service.SmartaccountingKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = SmartaccountingApp.class)
public class SmartaccountingKafkaResourceIT {

    @Autowired
    private SmartaccountingKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        SmartaccountingKafkaResource kafkaResource = new SmartaccountingKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/smartaccounting-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
