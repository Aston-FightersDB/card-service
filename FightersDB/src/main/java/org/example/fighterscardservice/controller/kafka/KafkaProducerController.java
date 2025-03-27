package org.example.fighterscardservice.controller.kafka;

import java.util.UUID;
import org.example.fighterscardservice.dto.KafkaDto.EventFinishedDto;
import org.example.fighterscardservice.service.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaProducerController {
    private final KafkaProducerService kafkaProducerService;

    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return "Message sent: " + message;
    }

    @PostMapping("/event-finished")
    public String finishBattle(@RequestParam UUID eventId, @RequestParam UUID winnerId, @RequestParam UUID loserId) {
        EventFinishedDto dto = EventFinishedDto.builder().eventId(eventId).loserId(loserId).winnerId(winnerId).build();
        kafkaProducerService.sendEventFinished(dto);
        return "Event " + eventId + " finished: " + winnerId + " won against " + loserId;
    }
}
