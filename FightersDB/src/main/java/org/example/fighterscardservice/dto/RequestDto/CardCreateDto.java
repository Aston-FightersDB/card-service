package org.example.fighterscardservice.dto.RequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCreateDto {
    private String name;
    private String arena;

    public CardCreateDto(String name, String arena) {
        this.name = name;
        this.arena = arena;
    }
}
