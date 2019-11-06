package com.byinal.rockpaperscissors.domain.model;

public class Game {

    private Integer winningScore;
    private GameType gameType;

    public Game(Integer winningScore, GameType gameType) {
        this.winningScore = winningScore;
        this.gameType = gameType;
    }

    public void setWinningScore(Integer winningScore) {
        this.winningScore = winningScore;
    }

    public Integer getWinningScore() {
        return winningScore;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public GameType getGameType() {
        return gameType;
    }
}
