package org.example.fighterscardservice.mapper;

import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.dto.ResponseDto.ResultDto;
import org.example.fighterscardservice.dto.ResponseDto.CardDto;
import org.example.fighterscardservice.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ResultMapper.class})
public interface EventMapper {

    default EventDto toDto(Event event) {
        if (event == null) {
            return null;
        }

        ResultDto resultDto = event.getResult() != null
                ? Mappers.getMapper(ResultMapper.class).toDto(event.getResult())
                : null;

        UUID cardId = event.getCard() != null ? event.getCard().getId() : null;

        return new EventDto(
                event.getId(),
                event.getRed_fighter_id(),
                event.getBlue_fighter_id(),
                resultDto,
                cardId// Только id карты
        );
    }

    default Iterable<EventDto> toDtoList(Iterable<Event> events) {
        return java.util.stream.StreamSupport.stream(events.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
