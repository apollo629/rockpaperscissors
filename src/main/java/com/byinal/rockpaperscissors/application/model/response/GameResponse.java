package com.byinal.rockpaperscissors.application.model.response;

public class GameResponse extends Response {

    private String id;
    private Integer winningScore;
    private String gameType;
    private String gameStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWinningScore() {
        return winningScore;
    }

    public void setWinningScore(Integer winningScore) {
        this.winningScore = winningScore;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}


