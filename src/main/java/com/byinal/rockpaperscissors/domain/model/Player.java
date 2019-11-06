package com.byinal.rockpaperscissors.domain.model;

import com.byinal.rockpaperscissors.domain.model.rule.Move;

public abstract class Player {

    private Move move;
    private Integer score;

    public abstract Move getMove();

    public void setMove(Move move) {
        this.move = move;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
