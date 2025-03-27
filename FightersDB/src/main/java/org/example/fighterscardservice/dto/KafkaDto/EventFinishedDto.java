package org.example.fighterscardservice.dto.KafkaDto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventFinishedDto {
    private UUID eventId;
    private UUID loserId;
    private UUID winnerId;
}
