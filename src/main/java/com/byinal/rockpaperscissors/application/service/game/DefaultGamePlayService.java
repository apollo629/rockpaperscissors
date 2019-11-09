package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.service.round.RoundService;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.round.Round;
import com.byinal.rockpaperscissors.domain.service.player.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class DefaultGamePlayService implements GamePlayService {

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
        return game;
    }
}
