package org.example.fighterscardservice.controller;

import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {

        EventCreateDto eventCreateDto = new EventCreateDto(1L, 2L, UUID.randomUUID(), null);
        EventDto expectedEventDto = new EventDto(UUID.randomUUID(), 1L, 2L, null, null, "Red fighter");

        when(eventService.createEvent(eventCreateDto)).thenReturn(expectedEventDto);

        ResponseEntity<EventDto> response = eventController.createEvent(eventCreateDto);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(expectedEventDto.getRed_fighter_id(), response.getBody().getRed_fighter_id());
        assertEquals(expectedEventDto.getBlue_fighter_id(), response.getBody().getBlue_fighter_id());
    }

    @Test
    void testGetEvent() {

        UUID eventId = UUID.randomUUID();
        EventDto expectedEventDto = new EventDto(eventId, 1L, 2L, null, null, "Red fighter");

        when(eventService.getEvent(eventId)).thenReturn(expectedEventDto);

        ResponseEntity<EventDto> response = eventController.getEvent(eventId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedEventDto.getId(), response.getBody().getId());
    }

    @Test
    void testUpdateEvent() {

        UUID eventId = UUID.randomUUID();
        EventCreateDto eventCreateDto = new EventCreateDto(3L, 4L, UUID.randomUUID(), null);
        EventDto updatedEventDto = new EventDto(eventId, 3L, 4L, null, null, "Blue fighter");

        when(eventService.updateEvent(eventId, eventCreateDto)).thenReturn(updatedEventDto);

        ResponseEntity<EventDto> response = eventController.updateEvent(eventId, eventCreateDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedEventDto.getRed_fighter_id(), response.getBody().getRed_fighter_id());
        assertEquals(updatedEventDto.getBlue_fighter_id(), response.getBody().getBlue_fighter_id());
    }

    @Test
    void testDeleteEvent() {

        UUID eventId = UUID.randomUUID();

        ResponseEntity<Void> response = eventController.deleteEvent(eventId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(eventService, times(1)).deleteEvent(eventId);
    }

    @Test
    void testGetAllEvents() {

        EventDto eventDto1 = new EventDto(UUID.randomUUID(), 1L, 2L, null, null, "Red fighter");
        EventDto eventDto2 = new EventDto(UUID.randomUUID(), 3L, 4L, null, null, "Blue fighter");

        when(eventService.getAllEvents()).thenReturn(List.of(eventDto1, eventDto2));

        ResponseEntity<Iterable<EventDto>> response = eventController.getAllEvents();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

}