package org.example.fighterscardservice.service.impl;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.example.fighterscardservice.dto.RequestDto.ResultCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.ResultDto;
import org.example.fighterscardservice.entity.Result;
import org.example.fighterscardservice.mapper.ResultMapper;
import org.example.fighterscardservice.repository.ResultRepository;
import org.example.fighterscardservice.service.EventService;
import org.example.fighterscardservice.service.ResultService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;

    @Override
    public ResultDto createResult(ResultCreateDto resultCreateDto) {
        Result result = Result.builder()
                .winner(resultCreateDto.getWinner())
                .bonus(resultCreateDto.getBonus())
                .build();
        return resultMapper.toDto(resultRepository.save(result));
    }

    @Override
    public ResultDto getResult(UUID resultId) {
        Optional<Result> result = resultRepository.findById(resultId);
        if (result.isPresent()) {
            return resultMapper.toDto(result.get());
        }
        throw new RuntimeException("Result not found with id: " + resultId);
    }

    @Override
    public ResultDto updateResult(UUID resultId, ResultCreateDto resultCreateDto) {
        Optional<Result> optionalResult = resultRepository.findById(resultId);
        if (optionalResult.isPresent()) {
            Result result = optionalResult.get();
            result.setWinner(resultCreateDto.getWinner());
            result.setBonus(resultCreateDto.getBonus());
            return resultMapper.toDto(resultRepository.save(result));
        }
        throw new RuntimeException("Result not found with id: " + resultId);
    }

    @Override
    public void deleteResult(UUID resultId) {
        if (!resultRepository.existsById(resultId)) {
            throw new RuntimeException("Result not found with id: " + resultId);
        }
        resultRepository.deleteById(resultId);
    }

    @Override
    public Iterable<ResultDto> getAllResults() {
        return resultRepository.findAll().stream()
                .map(resultMapper::toDto)
                .collect(Collectors.toList());
    }
}
