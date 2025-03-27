package org.example.fighterscardservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.logging.log4j.message.Message;
import org.example.fighterscardservice.dto.KafkaDto.EventFinishedDto;
import org.example.fighterscardservice.entity.Card;
import org.example.fighterscardservice.entity.Event;
import org.example.fighterscardservice.entity.Result;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String message) {
        kafkaTemplate.send("message-topic", message);
        System.out.println("Sent message: " + message);
    }

    public void sendEventFinished(EventFinishedDto eventFinishedDto) {
        UUID correlationId = UUID.randomUUID();
        kafkaTemplate.send("event-finished", correlationId.toString(), eventFinishedDto);
        System.out.println("Sent event-finished event: " + eventFinishedDto);
    }
}