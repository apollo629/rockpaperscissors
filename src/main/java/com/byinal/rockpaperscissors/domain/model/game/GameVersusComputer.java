package com.byinal.rockpaperscissors.domain.model.game;

import com.byinal.rockpaperscissors.domain.model.player.Player;

public class GameVersusComputer extends Game {

    public GameVersusComputer(Integer winningScore, Player personPlayer, Player computerPlayer) {
        super(winningScore, GameType.COMPUTER, personPlayer, computerPlayer);
    }
}
