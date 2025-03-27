package org.example.fighterscardservice.service;

import org.example.fighterscardservice.dto.RequestDto.ResultCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.ResultDto;
import org.example.fighterscardservice.entity.Result;
import org.example.fighterscardservice.mapper.ResultMapper;
import org.example.fighterscardservice.repository.ResultRepository;
import org.example.fighterscardservice.service.impl.ResultServiceImpl;
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

class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private ResultMapper resultMapper;

    @InjectMocks
    private ResultServiceImpl resultService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(resultRepository.existsById(any(UUID.class))).thenReturn(true);
    }

    @Test
    void testCreateResult() {

        ResultCreateDto resultCreateDto = new ResultCreateDto(1, true);
        Result result = Result.builder().winner(1).bonus(true).build();
        ResultDto expectedResultDto = new ResultDto(UUID.randomUUID(), 1, true);

        when(resultRepository.save(any(Result.class))).thenReturn(result);
        when(resultMapper.toDto(result)).thenReturn(expectedResultDto);

        ResultDto resultDto = resultService.createResult(resultCreateDto);

        assertNotNull(resultDto);
        assertEquals(1, resultDto.getWinner());
        assertTrue(resultDto.isBonus());
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void testGetResult() {

        UUID resultId = UUID.randomUUID();
        Result result = Result.builder().id(resultId).winner(1).bonus(true).build();
        ResultDto expectedResultDto = new ResultDto(resultId, 1, true);

        when(resultRepository.findById(resultId)).thenReturn(Optional.of(result));
        when(resultMapper.toDto(result)).thenReturn(expectedResultDto);

        ResultDto resultDto = resultService.getResult(resultId);

        assertNotNull(resultDto);
        assertEquals(resultId, resultDto.getId());
        assertEquals(1, resultDto.getWinner());
        assertTrue(resultDto.isBonus());
        verify(resultRepository, times(1)).findById(resultId);
    }

    @Test
    void testUpdateResult() {

        UUID resultId = UUID.randomUUID();
        ResultCreateDto resultCreateDto = new ResultCreateDto(2, false);
        Result existingResult = Result.builder().id(resultId).winner(1).bonus(true).build();
        Result updatedResult = Result.builder().id(resultId).winner(2).bonus(false).build();
        ResultDto expectedResultDto = new ResultDto(resultId, 2, false);

        when(resultRepository.findById(resultId)).thenReturn(Optional.of(existingResult));
        when(resultRepository.save(any(Result.class))).thenReturn(updatedResult);
        when(resultMapper.toDto(updatedResult)).thenReturn(expectedResultDto);

        ResultDto resultDto = resultService.updateResult(resultId, resultCreateDto);

        assertNotNull(resultDto);
        assertEquals(2, resultDto.getWinner());
        assertFalse(resultDto.isBonus());
        verify(resultRepository, times(1)).findById(resultId);
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void testDeleteResult() {

        UUID resultId = UUID.randomUUID();

        resultService.deleteResult(resultId);

        verify(resultRepository, times(1)).existsById(resultId);
        verify(resultRepository, times(1)).deleteById(resultId);
    }

    @Test
    void testGetAllResults() {

        Result result1 = Result.builder().id(UUID.randomUUID()).winner(1).bonus(true).build();
        Result result2 = Result.builder().id(UUID.randomUUID()).winner(2).bonus(false).build();
        ResultDto resultDto1 = new ResultDto(result1.getId(), 1, true);
        ResultDto resultDto2 = new ResultDto(result2.getId(), 2, false);

        when(resultRepository.findAll()).thenReturn(Stream.of(result1, result2).collect(Collectors.toList()));
        when(resultMapper.toDto(result1)).thenReturn(resultDto1);
        when(resultMapper.toDto(result2)).thenReturn(resultDto2);

        Iterable<ResultDto> result = resultService.getAllResults();

        assertNotNull(result);
        assertEquals(2, ((List<ResultDto>) result).size());
        verify(resultRepository, times(1)).findAll();
    }
}