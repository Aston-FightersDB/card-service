package org.example.fighterscardservice.dto.ResponseDto;

import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private UUID id;
    private UUID winner;
    private boolean bonus;

}
