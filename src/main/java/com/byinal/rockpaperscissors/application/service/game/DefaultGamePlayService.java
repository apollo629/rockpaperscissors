package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.service.round.RoundService;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.round.Round;
import com.byinal.rockpaperscissors.domain.service.player.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultGamePlayService implements GamePlayService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGamePlayService.class);

    private final GameStatusService gameStatusService;
    private final PlayerService playerService;
    private final RoundService defaultRoundService;

    public DefaultGamePlayService(GameStatusService gameStatusService, PlayerService playerService, RoundService defaultRoundService) {
        this.gameStatusService = gameStatusService;
        this.playerService = playerService;
        this.defaultRoundService = defaultRoundService;
    }

    @Override
    public Game play(Game game, PlayRequest playRequest) {
        gameStatusService.validateGameIsNotFinished(game);
        playerService.updatePlayerMove(game.getFirstPlayer(), playRequest.getMove());
        Round round = defaultRoundService.evaluate(game.getFirstPlayer(), game.getSecondPlayer());
        playerService.updatePlayerScore(round.getWinner());
        game.setRound(round);
        game = gameStatusService.updateStatus(game);
        logger.info("Player scores player1: {}, player2: {}", game.getFirstPlayer().getScore(), game.getSecondPlayer().getScore());
        logger.info("Game status after this round: {}", game.getGameStatus().name());
        return game;
    }
}
