package org.example.fighterscardservice.controller;

import java.util.UUID;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.fighterscardservice.dto.RequestDto.CardCreateDto;
import org.example.fighterscardservice.dto.RequestDto.CardUpdateDto;
import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.CardDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ufc")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/card")
    public ResponseEntity<CardDto> createCard(@RequestBody CardCreateDto cardCreateDto) {
        return new ResponseEntity<>(cardService.createCard(cardCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<CardDto> getCard(@PathVariable UUID cardId) {
        return new ResponseEntity<>(cardService.getCard(cardId), HttpStatus.OK);
    }

    @PutMapping("/card/{cardId}")
    public ResponseEntity<CardDto> updateCard(@PathVariable UUID cardId, @RequestBody CardUpdateDto cardUpdateDto) {
        return new ResponseEntity<>(cardService.updateCard(cardId, cardUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/card/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID cardId) {
        cardService.deleteCard(cardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/card")
    public ResponseEntity<Iterable<CardDto>> getAllCards() {
        return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
    }

    @GetMapping("/card/{cardId}/event")
    public ResponseEntity<Iterable<EventDto>> getEventsByCard(@PathVariable UUID cardId) {
        return new ResponseEntity<>(cardService.getEventsByCard(cardId), HttpStatus.OK);
    }

    @PostMapping("/card/{cardId}/event")
    public ResponseEntity<Void> addEventToCard(@PathVariable UUID cardId, @RequestBody EventCreateDto eventCreateDto) {
        cardService.addEventToCard(cardId, eventCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/card/{cardId}")
    public ResponseEntity<Void> updateCardPartially(@PathVariable UUID cardId, @RequestBody Map<String, Object> fields) {
        cardService.updateCardPartially(cardId, fields);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/card/{cardId}/event/{eventId}")
    public ResponseEntity<Void> updateEventPartially(
            @PathVariable UUID cardId,
            @PathVariable UUID eventId,
            @RequestBody Map<String, Object> fields) {
        cardService.updateEventPartially(cardId, eventId, fields);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
