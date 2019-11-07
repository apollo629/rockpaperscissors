package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.domain.exception.GameAlreadyFinishedException;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.game.GameStatus;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import org.springframework.stereotype.Service;

@Service
public class GameStatusService {

    public void validateGameIsNotFinished(Game game) {
        if (game.getGameStatus().isFinished()) {
            throw new GameAlreadyFinishedException("Game with id" + game.getId() + "is already finished.You cannot play another move at this game.");
        }
    }

    public Game updateStatus(Game game) {
        Player firstPlayer = game.getFirstPlayer();
        Player secondPlayer = game.getSecondPlayer();
        Integer winningScore = game.getWinningScore();
        if (firstPlayer.getScore().equals(winningScore) || secondPlayer.getScore().equals(winningScore)) {
            game.setGameStatus(GameStatus.FINISHED);
        }
        return game;
    }
}
