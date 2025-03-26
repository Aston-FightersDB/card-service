package org.example.fighterscardservice.dto.RequestDto;

public class ResultCreateDto {
    private int winner;
    private boolean bonus;

    public ResultCreateDto(int winner, boolean bonus) {
        this.winner = winner;
        this.bonus = bonus;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public boolean getBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

}
