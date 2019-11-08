package com.byinal.rockpaperscissors.application.model.response;

import com.byinal.rockpaperscissors.domain.model.game.Game;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    public GameResponse mapToGameResponse(Game game) {
        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(game.getId());
        gameResponse.setWinningScore(game.getWinningScore());
        gameResponse.setGameType(game.getGameType().name());
        gameResponse.setGameStatus(game.getGameStatus().name());
        return gameResponse;
    }

    public GamePlayResponse mapToGamePlayResponse(Game game) {
        GamePlayResponse gamePlayResponse = new GamePlayResponse();
        gamePlayResponse.setGameId(game.getId());
        gamePlayResponse.setGameStatus(game.getGameStatus());
        gamePlayResponse.setPlayer1(game.getFirstPlayer());
        gamePlayResponse.setPlayer2(game.getSecondPlayer());
        return gamePlayResponse;
    }
}
