package org.example.fighterscardservice.mapper;

import org.example.fighterscardservice.dto.ResponseDto.CardDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.dto.ResponseDto.ResultDto;
import org.example.fighterscardservice.entity.Card;
import org.example.fighterscardservice.entity.Event;
import org.example.fighterscardservice.entity.Result;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CardMapper {

    default CardDto toCardDto(Card card) {
        if (card == null) {
            return null;
        }

        List<EventDto> eventDtos = card.getEvents() != null
                ? card.getEvents().stream()
                .map(event -> toEventDto(event))
                .collect(Collectors.toList())
                : null;

        return new CardDto(
                card.getId(),
                card.getName(),
                card.getArena(),
                eventDtos
        );
    }

    default EventDto toEventDto(Event event) {
        if (event == null) {
            return null;
        }

        return new EventDto(
                event.getId(),
                event.getRed_fighter_id(),
                event.getBlue_fighter_id(),
                null,
                null,
                determineWinner(event.getResult())
        );
    }

    default String determineWinner(org.example.fighterscardservice.entity.Result result) {
        if (result == null) {
            return null;
        }
        return result.getWinner() == 1 ? "Red fighter" : "Blue fighter";
    }
}
