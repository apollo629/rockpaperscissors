package com.byinal.rockpaperscissors.application.model.response;

import com.byinal.rockpaperscissors.application.model.dto.PlayerDto;
import com.byinal.rockpaperscissors.application.model.dto.RoundDto;
import com.byinal.rockpaperscissors.application.model.dto.WinnerDto;
import com.byinal.rockpaperscissors.domain.model.game.GameStatus;

public class GamePlayResponse extends Response {

    private String gameId;
    private RoundDto roundDto;
    private PlayerDto player1;
    private PlayerDto player2;
    private GameStatus gameStatus;
    private WinnerDto winnerOfTheGame;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public RoundDto getRoundDto() {
        return roundDto;
    }

    public void setRoundDto(RoundDto roundDto) {
        this.roundDto = roundDto;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public PlayerDto getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerDto player1) {
        this.player1 = player1;
    }

    public PlayerDto getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerDto player2) {
        this.player2 = player2;
    }

    public WinnerDto getWinnerOfTheGame() {
        return winnerOfTheGame;
    }

    public void setWinnerOfTheGame(WinnerDto winnerOfTheGame) {
        this.winnerOfTheGame = winnerOfTheGame;
    }
}
