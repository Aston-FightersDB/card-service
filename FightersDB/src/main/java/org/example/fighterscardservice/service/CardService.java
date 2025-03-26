package org.example.fighterscardservice.service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.fighterscardservice.dto.RequestDto.CardCreateDto;
import org.example.fighterscardservice.dto.RequestDto.CardUpdateDto;
import org.example.fighterscardservice.dto.RequestDto.EventCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.CardDto;
import org.example.fighterscardservice.dto.ResponseDto.EventDto;
import org.example.fighterscardservice.entity.Card;
import org.example.fighterscardservice.entity.Event;
import org.example.fighterscardservice.entity.Result;
import org.example.fighterscardservice.mapper.CardMapper;
import org.example.fighterscardservice.mapper.EventMapper;
import org.example.fighterscardservice.repository.CardRepository;
import org.example.fighterscardservice.repository.EventRepository;
import org.example.fighterscardservice.repository.ResultRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final ResultRepository resultRepository;

    public CardDto createCard(CardCreateDto cardCreateDto) {
        Card card = Card.builder()
                .name(cardCreateDto.getName())
                .arena(cardCreateDto.getArena())
                .build();
        return cardMapper.toCardDto(cardRepository.save(card));
    }

    public CardDto getCard(UUID cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isPresent()) {
            return cardMapper.toCardDto(card.get());
        }
        throw new RuntimeException("Card not found with id: " + cardId);
    }

    public CardDto updateCard(UUID cardId, CardUpdateDto cardUpdateDto) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setName(cardUpdateDto.getName());
            card.setArena(cardUpdateDto.getArena());
            return cardMapper.toCardDto(cardRepository.save(card));
        }
        throw new RuntimeException("Card not found with id: " + cardId);
    }

    public void deleteCard(UUID cardId) {
        if (!cardRepository.existsById(cardId)) {
            throw new RuntimeException("Card not found with id: " + cardId);
        }
        cardRepository.deleteById(cardId);
    }

    public Iterable<CardDto> getAllCards() {
        return cardRepository.findAll().stream()
                .map(cardMapper::toCardDto)
                .collect(Collectors.toList());
    }

    public Iterable<EventDto> getEventsByCard(UUID cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        if (!card.isPresent()) {
            throw new RuntimeException("Card not found with id: " + cardId);
        }

        return card.get()
                .getEvents()
                .stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addEventToCard(UUID cardId, EventCreateDto eventCreateDto) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (!optionalCard.isPresent()) {
            throw new RuntimeException("Card not found with id: " + cardId);
        }

        Card card = optionalCard.get();

        Result result;
        if (eventCreateDto.getResult_id() != null) {

            Optional<Result> optionalResult = resultRepository.findById(eventCreateDto.getResult_id());
            if (!optionalResult.isPresent()) {
                throw new RuntimeException("Result not found with id: " + eventCreateDto.getResult_id());
            }
            result = optionalResult.get();
        } else {

            result = Result.builder()
                    .winner(0)
                    .bonus(false)
                    .build();
            result = resultRepository.save(result);
        }

        Event event = Event.builder()
                .red_fighter_id(eventCreateDto.getRed_fighter_id())
                .blue_fighter_id(eventCreateDto.getBlue_fighter_id())
                .result(result)
                .card(card)
                .build();

        eventRepository.save(event);
    }
}
