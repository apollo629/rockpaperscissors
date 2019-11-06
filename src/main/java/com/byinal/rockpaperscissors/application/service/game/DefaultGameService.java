package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.domain.model.Game;
import com.byinal.rockpaperscissors.domain.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService implements GameService {

    private final GameRepository inMemoryGameRepository;

    public DefaultGameService(GameRepository inMemoryGameRepository) {
        this.inMemoryGameRepository = inMemoryGameRepository;
    }

    @Override
    public GameResponse startGame(GameStartRequest gameStartRequest) {
        Game game = new Game(gameStartRequest.getWinningScore(), gameStartRequest.getGameType());
        String newGameId = inMemoryGameRepository.save(game);
        GameResponse gameResponse = new GameResponse(); //FIXME create a response mapper
        gameResponse.setId(newGameId);
        gameResponse.setWinningScore(game.getWinningScore());
        gameResponse.setGameType(game.getGameType().name());
        return gameResponse;
    }

    @Override
    public GameResponse retrieveGame(String id) {
        Game game = inMemoryGameRepository.findById(id);
        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(id); //FIXME id should be in the game object.
        gameResponse.setWinningScore(game.getWinningScore());
        gameResponse.setGameType(game.getGameType().name());
        return gameResponse;
    }

    @Override
    public void play(String gameId, PlayRequest playRequest) {
        Game game = inMemoryGameRepository.findById(gameId);
    }
}
