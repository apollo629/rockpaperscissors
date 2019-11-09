package com.byinal.rockpaperscissors.domain.model.game;

import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.round.Round;

public abstract class Game {

    private String id;
    private Integer winningScore;
    private GameType gameType;
    private GameStatus gameStatus = GameStatus.IN_PROGRESS;
    Player firstPlayer;
    Player secondPlayer;
    private Round round;
    private Player winner;

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

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }
}
