package org.example.fighterscardservice.service;

import java.util.UUID;
import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;

public interface EventService {

    EventDto createEvent(EventCreateDto eventCreateDto);

    EventDto getEvent(UUID eventId);

    EventDto updateEvent(UUID eventId, EventCreateDto eventCreateDto);

    void deleteEvent(UUID eventId);

    Iterable<EventDto> getAllEvents();
}
