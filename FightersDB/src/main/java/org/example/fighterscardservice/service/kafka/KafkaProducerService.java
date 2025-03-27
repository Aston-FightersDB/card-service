package org.example.fighterscardservice.service.kafka;

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
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("message-topic", message);
        System.out.println("Sent message: " + message);
    }

    public void sendCardFinished(Event event) {
        UUID loserId = event.getResult().getLoser();
        UUID winnerId = event.getResult().getWinner();
        EventFinishedDto dto = EventFinishedDto.builder().eventId(event.getId()).loserId(loserId).winnerId(winnerId).build();
        String message = "ID: " + dto.getEventId().toString() + " Winners IDs: " + dto.getWinnerId().toString()+ " Losers IDs: " + dto.getLoserId().toString();
        kafkaTemplate.send("event-finished", message);
        System.out.println("Sent card-finished event: " + message);
    }
}