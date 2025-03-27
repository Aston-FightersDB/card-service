package org.example.fighterscardservice.service;

import org.example.fighterscardservice.dto.RequestDto.CardCreateDto;
import org.example.fighterscardservice.dto.RequestDto.CardUpdateDto;
import org.example.fighterscardservice.dto.ResponseDto.CardDto;
import org.example.fighterscardservice.entity.Card;
import org.example.fighterscardservice.mapper.CardMapper;
import org.example.fighterscardservice.repository.CardRepository;
import org.example.fighterscardservice.service.impl.CardServiceImpl;
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

class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardMapper cardMapper;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(cardRepository.existsById(any(UUID.class))).thenReturn(true);
    }

    @Test
    void testCreateCard() {

        CardCreateDto cardCreateDto = new CardCreateDto("UFC 298", "Las Vegas Stadium");
        Card expectedCard = Card.builder().name("UFC 298").arena("Las Vegas Stadium").build();
        CardDto expectedCardDto = new CardDto(UUID.randomUUID(), "UFC 298", "Las Vegas Stadium", null);

        when(cardRepository.save(any(Card.class))).thenReturn(expectedCard);
        when(cardMapper.toCardDto(expectedCard)).thenReturn(expectedCardDto);

        CardDto result = cardService.createCard(cardCreateDto);

        assertNotNull(result);
        assertEquals("UFC 298", result.getName());
        assertEquals("Las Vegas Stadium", result.getArena());
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void testGetCard() {

        UUID cardId = UUID.randomUUID();
        Card card = Card.builder().id(cardId).name("UFC 298").arena("Las Vegas Stadium").build();
        CardDto expectedCardDto = new CardDto(cardId, "UFC 298", "Las Vegas Stadium", null);

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(cardMapper.toCardDto(card)).thenReturn(expectedCardDto);

        CardDto result = cardService.getCard(cardId);

        assertNotNull(result);
        assertEquals("UFC 298", result.getName());
        assertEquals("Las Vegas Stadium", result.getArena());
        verify(cardRepository, times(1)).findById(cardId);
    }

    @Test
    void testUpdateCard() {

        UUID cardId = UUID.randomUUID();
        Card existingCard = Card.builder().id(cardId).name("UFC 298").arena("Las Vegas Stadium").build();
        CardUpdateDto cardUpdateDto = new CardUpdateDto("UFC 299", "New York Arena");
        Card updatedCard = Card.builder().id(cardId).name("UFC 299").arena("New York Arena").build();
        CardDto expectedCardDto = new CardDto(cardId, "UFC 299", "New York Arena", null);

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(existingCard));
        when(cardRepository.save(any(Card.class))).thenReturn(updatedCard);
        when(cardMapper.toCardDto(updatedCard)).thenReturn(expectedCardDto);

        CardDto result = cardService.updateCard(cardId, cardUpdateDto);

        assertNotNull(result);
        assertEquals("UFC 299", result.getName());
        assertEquals("New York Arena", result.getArena());
        verify(cardRepository, times(1)).findById(cardId);
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void testDeleteCard() {

        UUID cardId = UUID.randomUUID();

        cardService.deleteCard(cardId);

        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, times(1)).deleteById(cardId);
    }

    @Test
    void testGetAllCards() {

        Card card1 = Card.builder().id(UUID.randomUUID()).name("UFC 298").arena("Las Vegas Stadium").build();
        Card card2 = Card.builder().id(UUID.randomUUID()).name("UFC 299").arena("New York Arena").build();
        CardDto cardDto1 = new CardDto(card1.getId(), "UFC 298", "Las Vegas Stadium", null);
        CardDto cardDto2 = new CardDto(card2.getId(), "UFC 299", "New York Arena", null);

        when(cardRepository.findAll()).thenReturn(Stream.of(card1, card2).collect(Collectors.toList()));
        when(cardMapper.toCardDto(card1)).thenReturn(cardDto1);
        when(cardMapper.toCardDto(card2)).thenReturn(cardDto2);

        Iterable<CardDto> result = cardService.getAllCards();

        assertNotNull(result);
        assertEquals(2, ((List<CardDto>) result).size());
        verify(cardRepository, times(1)).findAll();
    }
}