package org.example.fighterscardservice.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ufc")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventCreateDto eventCreateDto) {
        return new ResponseEntity<>(eventService.createEvent(eventCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable UUID eventId) {
        return new ResponseEntity<>(eventService.getEvent(eventId), HttpStatus.OK);
    }

    @PutMapping("/event/{eventId}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable UUID eventId, @RequestBody EventCreateDto eventCreateDto) {
        return new ResponseEntity<>(eventService.updateEvent(eventId, eventCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/event")
    public ResponseEntity<Iterable<EventDto>> getAllEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }
}
