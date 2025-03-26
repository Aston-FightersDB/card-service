package org.example.fighterscardservice.controller;

import org.example.fighterscardservice.dto.RequestDto.CardCreateDto;
import org.example.fighterscardservice.dto.RequestDto.CardUpdateDto;
import org.example.fighterscardservice.dto.ResponseDto.CardDto;
import org.example.fighterscardservice.service.CardService;
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

class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCard() {

        CardCreateDto cardCreateDto = new CardCreateDto("UFC 298", "Las Vegas Stadium");
        CardDto expectedCardDto = new CardDto(UUID.randomUUID(), "UFC 298", "Las Vegas Stadium", null);

        when(cardService.createCard(cardCreateDto)).thenReturn(expectedCardDto);

        ResponseEntity<CardDto> response = cardController.createCard(cardCreateDto);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(expectedCardDto.getName(), response.getBody().getName());
        assertEquals(expectedCardDto.getArena(), response.getBody().getArena());
    }

    @Test
    void testGetCard() {

        UUID cardId = UUID.randomUUID();
        CardDto expectedCardDto = new CardDto(cardId, "UFC 298", "Las Vegas Stadium", null);

        when(cardService.getCard(cardId)).thenReturn(expectedCardDto);

        ResponseEntity<CardDto> response = cardController.getCard(cardId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedCardDto.getId(), response.getBody().getId());
    }

    @Test
    void testUpdateCard() {

        UUID cardId = UUID.randomUUID();
        CardUpdateDto cardUpdateDto = new CardUpdateDto("UFC 299", "New Arena");
        CardDto expectedCardDto = new CardDto(cardId, "UFC 299", "New Arena", null);

        when(cardService.updateCard(cardId, cardUpdateDto)).thenReturn(expectedCardDto);

        ResponseEntity<CardDto> response = cardController.updateCard(cardId, cardUpdateDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedCardDto.getName(), response.getBody().getName());
        assertEquals(expectedCardDto.getArena(), response.getBody().getArena());
    }

    @Test
    void testDeleteCard() {

        UUID cardId = UUID.randomUUID();

        ResponseEntity<Void> response = cardController.deleteCard(cardId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(cardService, times(1)).deleteCard(cardId);
    }

    @Test
    void testGetAllCards() {

        CardDto cardDto1 = new CardDto(UUID.randomUUID(), "UFC 298", "Las Vegas Stadium", null);
        CardDto cardDto2 = new CardDto(UUID.randomUUID(), "UFC 299", "New York Arena", null);

        when(cardService.getAllCards()).thenReturn(List.of(cardDto1, cardDto2));

        ResponseEntity<Iterable<CardDto>> response = cardController.getAllCards();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, ((List<CardDto>) response.getBody()).size());
    }
}