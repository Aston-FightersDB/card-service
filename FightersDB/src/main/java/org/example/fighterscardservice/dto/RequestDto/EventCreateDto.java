package org.example.fighterscardservice.dto.RequestDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EventCreateDto {

    private Long red_fighter_id;
    private Long blue_fighter_id;
    private UUID card_id;
    private UUID result_id;

    public EventCreateDto(Long red_fighter_id, Long blue_fighter_id, UUID card_id, UUID result_id) {
        this.red_fighter_id = red_fighter_id;
        this.blue_fighter_id = blue_fighter_id;
        this.card_id = card_id;
        this.result_id = result_id;
    }

    public EventCreateDto() {}
}
