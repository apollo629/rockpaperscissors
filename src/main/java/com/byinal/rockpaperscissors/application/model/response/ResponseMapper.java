package com.byinal.rockpaperscissors.application.model.response;

import com.byinal.rockpaperscissors.application.converter.PlayerDtoConverter;
import com.byinal.rockpaperscissors.application.converter.RoundDtoConverter;
import com.byinal.rockpaperscissors.application.converter.WinnerDtoConverter;
import com.byinal.rockpaperscissors.application.model.dto.RoundDto;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    private final RoundDtoConverter roundDtoConverter;
    private final WinnerDtoConverter winnerDtoConverter;
    private final PlayerDtoConverter playerDtoConverter;

    public ResponseMapper(RoundDtoConverter roundDtoConverter,
                          WinnerDtoConverter winnerDtoConverter,
                          PlayerDtoConverter playerDtoConverter) {
        this.roundDtoConverter = roundDtoConverter;
        this.winnerDtoConverter = winnerDtoConverter;
        this.playerDtoConverter = playerDtoConverter;
    }

    public GameResponse mapToGameResponse(Game game) {
        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(game.getId());
        gameResponse.setWinningScore(game.getWinningScore());
        gameResponse.setGameType(game.getGameType().name());
        gameResponse.setGameStatus(game.getGameStatus().name());
        gameResponse.setStatus(ResponseStatus.SUCCESS.name());
        return gameResponse;
    }

    public GamePlayResponse mapToGamePlayResponse(Game game) {
        GamePlayResponse gamePlayResponse = new GamePlayResponse();
        gamePlayResponse.setGameId(game.getId());
        RoundDto roundDto = roundDtoConverter.convert(game.getRound());
        gamePlayResponse.setRoundDto(roundDto);
        gamePlayResponse.setGameStatus(game.getGameStatus());
        gamePlayResponse.setPlayer1(playerDtoConverter.convert(game.getFirstPlayer()));
        gamePlayResponse.setPlayer2(playerDtoConverter.convert(game.getSecondPlayer()));
        if (game.getGameStatus().isFinished()) {
            Player winner = game.getWinner();
            gamePlayResponse.setWinnerOfTheGame(winnerDtoConverter.convert(winner));
        }
        gamePlayResponse.setStatus(ResponseStatus.SUCCESS.name());
        return gamePlayResponse;
    }
}
