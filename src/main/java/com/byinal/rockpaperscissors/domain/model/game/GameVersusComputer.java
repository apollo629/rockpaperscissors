package com.byinal.rockpaperscissors.domain.model.game;

import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.rule.Move;

public class GameVersusComputer extends Game {

    public GameVersusComputer(Integer winningScore, Player personPlayer, Player computerPlayer) {
        super(winningScore, GameType.COMPUTER, personPlayer, computerPlayer);
    }

    @Override
    public void setPlayerMove(Move move) {
        firstPlayer.setMove(move);
    }
}
