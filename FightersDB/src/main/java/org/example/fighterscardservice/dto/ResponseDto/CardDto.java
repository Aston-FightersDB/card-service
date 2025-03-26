package org.example.fighterscardservice.dto.ResponseDto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private UUID id;
    private String name;
    private String arena;
    private List<EventDto> events;

}
