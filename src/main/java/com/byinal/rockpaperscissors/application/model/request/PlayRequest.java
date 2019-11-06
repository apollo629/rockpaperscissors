package com.byinal.rockpaperscissors.application.model.request;

import com.byinal.rockpaperscissors.domain.model.rule.Move;

public class PlayRequest {

    private Integer playerNo;
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
