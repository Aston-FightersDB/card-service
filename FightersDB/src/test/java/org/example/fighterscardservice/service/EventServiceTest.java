package org.example.fighterscardservice.service;

import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.entity.Card;
import org.example.fighterscardservice.entity.Event;
import org.example.fighterscardservice.entity.Result;
import org.example.fighterscardservice.mapper.EventMapper;
import org.example.fighterscardservice.repository.CardRepository;
import org.example.fighterscardservice.repository.EventRepository;
import org.example.fighterscardservice.repository.ResultRepository;
import org.example.fighterscardservice.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(eventRepository.existsById(any(UUID.class))).thenReturn(true);
    }

    @Test
    void testCreateEvent() {

        UUID cardId = UUID.randomUUID();
        UUID resultId = UUID.randomUUID();
        EventCreateDto eventCreateDto = new EventCreateDto(1L, 2L, cardId, resultId);
        Card card = Card.builder().id(cardId).build();
        Result result = Result.builder().id(resultId).build();
        Event event = Event.builder().id(UUID.randomUUID()).red_fighter_id(1L).blue_fighter_id(2L).card(card).result(result).build();
        EventDto expectedEventDto = new EventDto(event.getId(), 1L, 2L, null, null, "Red fighter");

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(resultRepository.findById(resultId)).thenReturn(Optional.of(result));
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        when(eventMapper.toDto(event)).thenReturn(expectedEventDto);

        EventDto resultDto = eventService.createEvent(eventCreateDto);

        assertNotNull(resultDto);
        assertEquals(1L, resultDto.getRed_fighter_id());
        assertEquals(2L, resultDto.getBlue_fighter_id());
        verify(cardRepository, times(1)).findById(cardId);
        verify(resultRepository, times(1)).findById(resultId);
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void testGetEvent() {

        UUID eventId = UUID.randomUUID();
        Event event = Event.builder().id(eventId).red_fighter_id(1L).blue_fighter_id(2L).build();
        EventDto expectedEventDto = new EventDto(eventId, 1L, 2L, null, null, "Red fighter");

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventMapper.toDto(event)).thenReturn(expectedEventDto);

        EventDto result = eventService.getEvent(eventId);

        assertNotNull(result);
        assertEquals(eventId, result.getId());
        assertEquals(1L, result.getRed_fighter_id());
        assertEquals(2L, result.getBlue_fighter_id());
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    void testUpdateEvent() {

        UUID eventId = UUID.randomUUID();
        EventCreateDto eventCreateDto = new EventCreateDto(3L, 4L, null, null);
        Event existingEvent = Event.builder().id(eventId).red_fighter_id(1L).blue_fighter_id(2L).build();
        Event updatedEvent = Event.builder().id(eventId).red_fighter_id(3L).blue_fighter_id(4L).build();
        EventDto expectedEventDto = new EventDto(eventId, 3L, 4L, null, null, "Blue fighter");

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(any(Event.class))).thenReturn(updatedEvent);
        when(eventMapper.toDto(updatedEvent)).thenReturn(expectedEventDto);

        EventDto result = eventService.updateEvent(eventId, eventCreateDto);

        assertNotNull(result);
        assertEquals(3L, result.getRed_fighter_id());
        assertEquals(4L, result.getBlue_fighter_id());
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void testDeleteEvent() {

        UUID eventId = UUID.randomUUID();

        eventService.deleteEvent(eventId);

        verify(eventRepository, times(1)).existsById(eventId);
        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    void testGetAllEvents() {

        Event event1 = Event.builder().id(UUID.randomUUID()).red_fighter_id(1L).blue_fighter_id(2L).build();
        Event event2 = Event.builder().id(UUID.randomUUID()).red_fighter_id(3L).blue_fighter_id(4L).build();
        EventDto eventDto1 = new EventDto(event1.getId(), 1L, 2L, null, null, "Red fighter");
        EventDto eventDto2 = new EventDto(event2.getId(), 3L, 4L, null, null, "Blue fighter");

        when(eventRepository.findAll()).thenReturn(Stream.of(event1, event2).collect(Collectors.toList()));
        when(eventMapper.toDto(event1)).thenReturn(eventDto1);
        when(eventMapper.toDto(event2)).thenReturn(eventDto2);

        Iterable<EventDto> result = eventService.getAllEvents();

        assertNotNull(result);
        assertEquals(2, ((List<EventDto>) result).size());
        verify(eventRepository, times(1)).findAll();
    }
}