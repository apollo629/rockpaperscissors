package com.byinal.rockpaperscissors.application.model.request;

import com.byinal.rockpaperscissors.domain.model.rule.Move;

public class PlayRequest {

    private Move firstPlayerMove;
    private Move secondPlayerMove;

    public Move getFirstPlayerMove() {
        return firstPlayerMove;
    }

    public void setFirstPlayerMove(Move firstPlayerMove) {
        this.firstPlayerMove = firstPlayerMove;
    }

    public Move getSecondPlayerMove() {
        return secondPlayerMove;
    }

    public void setSecondPlayerMove(Move secondPlayerMove) {
        this.secondPlayerMove = secondPlayerMove;
    }
}
