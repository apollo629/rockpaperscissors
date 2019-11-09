package com.byinal.rockpaperscissors.application.model.dto;

import com.byinal.rockpaperscissors.domain.model.player.PlayerType;

public class WinnerDto {

    private PlayerType playerType;
    private Integer score;

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
