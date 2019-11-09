package com.byinal.rockpaperscissors.application.model.request;

import com.byinal.rockpaperscissors.domain.model.Move;

import javax.validation.constraints.NotNull;

public class PlayRequest {

    private Integer playerNo;

    @NotNull(message = "Move cannot be null")
    private Move move;

    public Integer getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(Integer playerNo) {
        this.playerNo = playerNo;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
