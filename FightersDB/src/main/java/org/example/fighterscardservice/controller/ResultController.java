package org.example.fighterscardservice.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.fighterscardservice.dto.RequestDto.ResultCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.ResultDto;
import org.example.fighterscardservice.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-service")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/result")
    public ResponseEntity<ResultDto> createResult(@RequestBody ResultCreateDto resultCreateDto) {
        return new ResponseEntity<>(resultService.createResult(resultCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/result/{resultId}")
    public ResponseEntity<ResultDto> getResult(@PathVariable UUID resultId) {
        return new ResponseEntity<>(resultService.getResult(resultId), HttpStatus.OK);
    }

    @PutMapping("/result/{resultId}")
    public ResponseEntity<ResultDto> updateResult(@PathVariable UUID resultId, @RequestBody ResultCreateDto resultCreateDto) {
        return new ResponseEntity<>(resultService.updateResult(resultId, resultCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/result/{resultId}")
    public ResponseEntity<Void> deleteResult(@PathVariable UUID resultId) {
        resultService.deleteResult(resultId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/result")
    public ResponseEntity<Iterable<ResultDto>> getAllResults() {
        return new ResponseEntity<>(resultService.getAllResults(), HttpStatus.OK);
    }
}
