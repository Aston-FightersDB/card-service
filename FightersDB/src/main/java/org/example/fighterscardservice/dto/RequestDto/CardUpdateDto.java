package org.example.fighterscardservice.dto.RequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUpdateDto {
    private String name;
    private String arena;

    public CardUpdateDto(String name, String arena) {
        this.name = name;
        this.arena = arena;
    }
}
