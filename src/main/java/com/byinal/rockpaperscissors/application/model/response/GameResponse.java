package com.byinal.rockpaperscissors.application.model.response;

public class GameResponse {

    private String id;
    private Integer winningScore;
    private String gameType;
    private String status = "IN_PROGRESS";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


