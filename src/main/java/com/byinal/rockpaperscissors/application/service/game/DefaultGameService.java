package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.application.model.response.ResponseMapper;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.game.GameFactory;
import com.byinal.rockpaperscissors.domain.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService implements GameService {

    private final GameFactory gameFactory;
    private final GameRepository inMemoryGameRepository;
    private final RoundService roundService;
    private final GameStatusService gameStatusService;
    private final ResponseMapper responseMapper;

    public DefaultGameService(GameFactory gameFactory,
                              GameRepository inMemoryGameRepository,
                              RoundService roundService,
                              GameStatusService gameStatusService,
                              ResponseMapper responseMapper) {
        this.gameFactory = gameFactory;
        this.inMemoryGameRepository = inMemoryGameRepository;
        this.roundService = roundService;
        this.gameStatusService = gameStatusService;
        this.responseMapper = responseMapper;
    }

    @Override
    public GameResponse startGame(GameStartRequest gameStartRequest) {
        Game game = gameFactory.createGame(gameStartRequest.getWinningScore(), gameStartRequest.getGameType());
        inMemoryGameRepository.save(game);
        return responseMapper.mapToGameResponse(game);
    }

    @Override
    public GameResponse retrieveGame(String id) {
        Game game = inMemoryGameRepository.findById(id);
        return responseMapper.mapToGameResponse(game);
    }

    @Override
    public GamePlayResponse play(String gameId, PlayRequest playRequest) {
        Game game = inMemoryGameRepository.findById(gameId);
        gameStatusService.validateGameIsNotFinished(game);
        game.getFirstPlayer().setMove(playRequest.getMove());
        roundService.evaluate(game.getFirstPlayer(), game.getSecondPlayer());
        game = gameStatusService.updateStatus(game);
        return responseMapper.mapToGamePlayResponse(game);
    }
}
