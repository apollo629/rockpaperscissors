package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService implements GameService {

    private final GameRepository inMemoryGameRepository;
    private final RoundService roundService;
    private final GameStatusService gameStatusService;

    public DefaultGameService(GameRepository inMemoryGameRepository,
                              RoundService roundService,
                              GameStatusService gameStatusService) {
        this.inMemoryGameRepository = inMemoryGameRepository;
        this.roundService = roundService;
        this.gameStatusService = gameStatusService;
    }

    @Override
    public GameResponse startGame(GameStartRequest gameStartRequest) {
        Game game = new Game.GameBuilder()
                .winningScore(gameStartRequest.getWinningScore())
                .gameType(gameStartRequest.getGameType())
                .build();
        String newGameId = inMemoryGameRepository.save(game);
        GameResponse gameResponse = new GameResponse(); //FIXME create a response mapper
        gameResponse.setId(newGameId);
        gameResponse.setWinningScore(game.getWinningScore());
        gameResponse.setGameType(game.getGameType().name());
        gameResponse.setStatus(game.getGameStatus().name());
        return gameResponse;
    }

    @Override
    public GameResponse retrieveGame(String id) {
        Game game = inMemoryGameRepository.findById(id);
        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(id); //FIXME id should be in the game object.
        gameResponse.setWinningScore(game.getWinningScore());
        gameResponse.setGameType(game.getGameType().name());
        gameResponse.setStatus(game.getGameStatus().name());
        return gameResponse;
    }

    @Override
    public GamePlayResponse play(String gameId, PlayRequest playRequest) {
        Game game = inMemoryGameRepository.findById(gameId); //TODO: check gameResult before play
        game.setPlayerMove(playRequest.getMove());
        roundService.evaluate(game.getFirstPlayer(), game.getSecondPlayer());
        gameStatusService.updateStatus(game);
        GamePlayResponse gamePlayResponse = new GamePlayResponse();
        gamePlayResponse.setGameId(gameId);
        gamePlayResponse.setGameStatus(game.getGameStatus());
        gamePlayResponse.setPlayer1(game.getFirstPlayer());
        gamePlayResponse.setPlayer2(game.getSecondPlayer());
        return gamePlayResponse;
    }
}
