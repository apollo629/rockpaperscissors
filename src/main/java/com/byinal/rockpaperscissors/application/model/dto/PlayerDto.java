package com.byinal.rockpaperscissors.application.model.dto;

import com.byinal.rockpaperscissors.domain.model.Move;
import com.byinal.rockpaperscissors.domain.model.player.PlayerType;

public class PlayerDto {

    private PlayerType type;
    private Move lastMove;
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

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }
}
