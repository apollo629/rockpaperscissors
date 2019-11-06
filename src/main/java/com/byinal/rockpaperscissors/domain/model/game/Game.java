package com.byinal.rockpaperscissors.domain.model.game;

import com.byinal.rockpaperscissors.domain.model.player.ComputerPlayer;
import com.byinal.rockpaperscissors.domain.model.player.PersonPlayer;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.rule.Move;

public class Game {

    private Integer winningScore;
    private GameType gameType;
    private Player firstPlayer;
    private Player secondPlayer;
    private GameStatus gameStatus = GameStatus.IN_PROGRESS;

    private Game(Integer winningScore, GameType gameType, Player firstPlayer, Player secondPlayer) {
        this.winningScore = winningScore;
        this.gameType = gameType;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setPlayerMove(Move move) {
        firstPlayer.setMove(move);
    }


    public static class GameBuilder {

        private Integer winningScore;
        private GameType gameType;
        private Player firstPlayer;
        private Player secondPlayer;

        public GameBuilder winningScore(Integer winningScore) {
            this.winningScore = winningScore;
            return this;
        }

        public GameBuilder gameType(GameType gameType) {
            this.gameType = gameType;
            return this;
        }

        public Game build() {
            if (this.gameType.isGameVersusComputer()) {
                this.firstPlayer = new PersonPlayer();
                this.secondPlayer = new ComputerPlayer();
            } else {
                this.firstPlayer = new PersonPlayer();
                this.secondPlayer = new PersonPlayer();
            }
            return new Game(this.winningScore, this.gameType, this.firstPlayer, this.secondPlayer);
        }
    }
}
