package org.example.fighterscardservice.mapper;

import org.example.fighterscardservice.dto.ResponseDto.ResultDto;
import org.example.fighterscardservice.entity.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResultMapper {

    default ResultDto toDto(Result result) {
        if (result == null) {
            return null;
        }
        return ResultDto.builder()
                .id(result.getId())
                .winner(result.getWinner())
                .bonus(result.getBonus())
                .build();
    }
}
