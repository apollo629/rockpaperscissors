package com.byinal.rockpaperscissors.application.model.dto;

import com.byinal.rockpaperscissors.domain.model.player.PlayerType;

public class WinnerDto {

    private PlayerType type;
    private Integer score;

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
