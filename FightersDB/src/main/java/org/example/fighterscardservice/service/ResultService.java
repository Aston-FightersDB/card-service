package org.example.fighterscardservice.service;

import java.util.UUID;
import org.example.fighterscardservice.dto.RequestDto.ResultCreateDto;
import org.example.fighterscardservice.dto.ResponseDto.ResultDto;

public interface ResultService {

    ResultDto createResult(ResultCreateDto resultCreateDto);

    ResultDto getResult(UUID resultId);

    ResultDto updateResult(UUID resultId, ResultCreateDto resultCreateDto);

    void deleteResult(UUID resultId);

    Iterable<ResultDto> getAllResults();

}
