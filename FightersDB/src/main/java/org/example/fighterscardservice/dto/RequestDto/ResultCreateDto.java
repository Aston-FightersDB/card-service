package org.example.fighterscardservice.dto.RequestDto;

import java.util.UUID;

public class ResultCreateDto {
    private UUID winner;
    private boolean bonus;

    public ResultCreateDto(UUID winner, boolean bonus) {
        this.winner = winner;
        this.bonus = bonus;
    }

    public UUID getWinner() {
        return winner;
    }

    public void setWinner(UUID winner) {
        this.winner = winner;
    }

    public boolean getBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

}
