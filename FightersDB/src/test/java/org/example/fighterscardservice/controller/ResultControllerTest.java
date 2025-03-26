package org.example.fighterscardservice.controller;

import org.example.fighterscardservice.dto.RequestDto.ResultCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.ResultDto;
import org.example.fighterscardservice.service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ResultControllerTest {

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateResult() {

        ResultCreateDto resultCreateDto = new ResultCreateDto(1, true);
        ResultDto expectedResultDto = new ResultDto(UUID.randomUUID(), 1, true);

        when(resultService.createResult(resultCreateDto)).thenReturn(expectedResultDto);

        ResponseEntity<ResultDto> response = resultController.createResult(resultCreateDto);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(expectedResultDto.getWinner(), response.getBody().getWinner());
        assertEquals(expectedResultDto.isBonus(), response.getBody().isBonus());
    }

    @Test
    void testGetResult() {

        UUID resultId = UUID.randomUUID();
        ResultDto expectedResultDto = new ResultDto(resultId, 1, true);

        when(resultService.getResult(resultId)).thenReturn(expectedResultDto);

        ResponseEntity<ResultDto> response = resultController.getResult(resultId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResultDto.getId(), response.getBody().getId());
        assertEquals(expectedResultDto.getWinner(), response.getBody().getWinner());
        assertEquals(expectedResultDto.isBonus(), response.getBody().isBonus());
    }

    @Test
    void testUpdateResult() {

        UUID resultId = UUID.randomUUID();
        ResultCreateDto resultCreateDto = new ResultCreateDto(2, false);
        ResultDto updatedResultDto = new ResultDto(resultId, 2, false);

        when(resultService.updateResult(resultId, resultCreateDto)).thenReturn(updatedResultDto);

        ResponseEntity<ResultDto> response = resultController.updateResult(resultId, resultCreateDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedResultDto.getWinner(), response.getBody().getWinner());
        assertEquals(updatedResultDto.isBonus(), response.getBody().isBonus());
    }

    @Test
    void testDeleteResult() {

        UUID resultId = UUID.randomUUID();

        ResponseEntity<Void> response = resultController.deleteResult(resultId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(resultService, times(1)).deleteResult(resultId);
    }

}