package com.byinal.rockpaperscissors.application.model.request;

import com.byinal.rockpaperscissors.domain.model.game.GameType;

public class GameStartRequest {

    private Integer winningScore = 3;
    private GameType gameType = GameType.COMPUTER;

    public Integer getWinningScore() {
        return winningScore;
    }

    public void setWinningScore(Integer winningScore) {
        this.winningScore = winningScore;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
