package org.example.fighterscardservice.dto.RequestDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EventCreateDto {

    private UUID red_fighter_id;
    private UUID blue_fighter_id;
    private UUID card_id;
    private UUID result_id;

    public EventCreateDto(UUID red_fighter_id, UUID blue_fighter_id, UUID card_id, UUID result_id) {
        this.red_fighter_id = red_fighter_id;
        this.blue_fighter_id = blue_fighter_id;
        this.card_id = card_id;
        this.result_id = result_id;
    }

    public EventCreateDto() {}
}
