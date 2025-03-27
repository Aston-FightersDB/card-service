package org.example.fighterscardservice.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "message-topic", groupId = "card-service-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    @KafkaListener(topics = "event-finished", groupId = "card-service-group")
    public void listenCardFinished(String message) {
        System.out.println("Received message: " + message);
    }
}