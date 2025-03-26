package org.example.fighterscardservice.service;

import java.util.UUID;
import org.example.fighterscardservice.dto.RequestDto.CardCreateDto;
import org.example.fighterscardservice.dto.RequestDto.CardUpdateDto;
import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.CardDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;

public interface CardService {

    CardDto createCard(CardCreateDto cardCreateDto);

    CardDto getCard(UUID cardId);

    CardDto updateCard(UUID cardId, CardUpdateDto cardUpdateDto);

    void deleteCard(UUID cardId);

    Iterable<CardDto> getAllCards();

    Iterable<EventDto> getEventsByCard(UUID cardId);

    void addEventToCard(UUID cardId, EventCreateDto eventCreateDto);

}
