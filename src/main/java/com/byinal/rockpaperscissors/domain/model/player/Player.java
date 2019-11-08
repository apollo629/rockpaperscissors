package com.byinal.rockpaperscissors.domain.model.player;

import com.byinal.rockpaperscissors.domain.model.rule.Move;

public abstract class Player {

    PlayerType type;
    private Move move;
    private Integer score = 0;

    public abstract Move move();

    public Move getMove(){return this.move; }

    public void setMove(Move move) {
        this.move = move;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public void incrementScore(){
        this.score += 1;
    }
}
