package com.byinal.rockpaperscissors.domain.model.player;

import com.byinal.rockpaperscissors.domain.model.rule.Move;

import java.util.Random;

public class ComputerPlayer extends Player {

    @Override
    public Move move() {
        Move move = Move.values()[new Random().nextInt(3)];
        this.setMove(move);
        return move;
    }
}
