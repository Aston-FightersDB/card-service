package org.example.fighterscardservice.dto.ResponseDto;

import java.util.UUID;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private UUID id;
    private Long red_fighter_id;
    private Long blue_fighter_id;
    private ResultDto result;
    private UUID card_id;
    private String resultWinner;

}
