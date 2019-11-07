package com.byinal.rockpaperscissors.domain.model.game;

import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.rule.Move;

public abstract class Game {

    private String id;
    private Integer winningScore;
    private GameType gameType;
    Player firstPlayer;
    Player secondPlayer;
    private GameStatus gameStatus = GameStatus.IN_PROGRESS;

    public Game(Integer winningScore, GameType gameType, Player firstPlayer, Player secondPlayer) {
        this.winningScore = winningScore;
        this.gameType = gameType;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

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

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public abstract void setPlayerMove(Move move);
}
