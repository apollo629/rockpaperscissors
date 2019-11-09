package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.application.model.response.ResponseMapper;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.game.GameFactory;
import com.byinal.rockpaperscissors.domain.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameManager implements GameManager {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGameManager.class);

    private final GameFactory gameFactory;
    private final GameRepository inMemoryGameRepository;
    private final DefaultGamePlayService defaultGamePlayService;
    private final ResponseMapper responseMapper;

    public DefaultGameManager(GameFactory gameFactory,
                              GameRepository inMemoryGameRepository,
                              DefaultGamePlayService defaultGamePlayService,
                              ResponseMapper responseMapper) {
        this.gameFactory = gameFactory;
        this.inMemoryGameRepository = inMemoryGameRepository;
        this.defaultGamePlayService = defaultGamePlayService;
        this.responseMapper = responseMapper;
    }

    @Override
    public GameResponse startGame(GameStartRequest gameStartRequest) {
        logger.info("Game will be started with winningScore: {} and type: {}", gameStartRequest.getWinningScore(), gameStartRequest.getGameType());
        Game game = gameFactory.createGame(gameStartRequest.getWinningScore(), gameStartRequest.getGameType());
        inMemoryGameRepository.save(game);
        logger.info("Game is started with id:{} and winningScore: {} and type: {}", game.getId(), game.getWinningScore(), game.getGameType());
        return responseMapper.mapToGameResponse(game);
    }

    @Override
    public GameResponse retrieveGame(String id) {
        logger.info("Game with id: {} will be retrieved", id);
        Game game = inMemoryGameRepository.findById(id);
        return responseMapper.mapToGameResponse(game);
    }

    @Override
    public GamePlayResponse play(String gameId, PlayRequest playRequest) {
        logger.info("Move: {} will be played at game with id: {}", playRequest.getMove(), gameId);
        Game game = inMemoryGameRepository.findById(gameId);
        game = defaultGamePlayService.play(game, playRequest);
        return responseMapper.mapToGamePlayResponse(game);
    }
}
