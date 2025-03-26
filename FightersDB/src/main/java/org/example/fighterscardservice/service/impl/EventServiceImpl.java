package org.example.fighterscardservice.service.impl;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.entity.Card;
import org.example.fighterscardservice.entity.Event;
import org.example.fighterscardservice.entity.Result;
import org.example.fighterscardservice.mapper.EventMapper;
import org.example.fighterscardservice.repository.CardRepository;
import org.example.fighterscardservice.repository.EventRepository;
import org.example.fighterscardservice.repository.ResultRepository;
import org.example.fighterscardservice.service.EventService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CardRepository cardRepository;
    private final ResultRepository resultRepository;
    private final EventMapper eventMapper;

    @Override
    public EventDto createEvent(EventCreateDto eventCreateDto) {
        Optional<Card> optionalCard = cardRepository.findById(eventCreateDto.getCard_id());
        if (!optionalCard.isPresent()) {
            throw new RuntimeException("Card not found with id: " + eventCreateDto.getCard_id());
        }

        Result result;
        if (eventCreateDto.getResult_id() != null) {
            Optional<Result> optionalResult = resultRepository.findById(eventCreateDto.getResult_id());
            if (!optionalResult.isPresent()) {
                throw new RuntimeException("Result not found with id: " + eventCreateDto.getResult_id());
            }
            result = optionalResult.get();
        } else {
            result = null;
        }

        Event event = Event.builder()
                .red_fighter_id(eventCreateDto.getRed_fighter_id())
                .blue_fighter_id(eventCreateDto.getBlue_fighter_id())
                .result(result)
                .card(optionalCard.get())
                .build();

        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventDto getEvent(UUID eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            return eventMapper.toDto(event.get());
        }
        throw new RuntimeException("Event not found with id: " + eventId);
    }

    @Override
    public EventDto updateEvent(UUID eventId, EventCreateDto eventCreateDto) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setRed_fighter_id(eventCreateDto.getRed_fighter_id());
            event.setBlue_fighter_id(eventCreateDto.getBlue_fighter_id());
            return eventMapper.toDto(eventRepository.save(event));
        }
        throw new RuntimeException("Event not found with id: " + eventId);
    }

    @Override
    public void deleteEvent(UUID eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found with id: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }

    @Override
    public Iterable<EventDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }
}
