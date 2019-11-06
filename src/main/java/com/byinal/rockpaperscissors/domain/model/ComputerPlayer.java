package com.byinal.rockpaperscissors.domain.model;

import com.byinal.rockpaperscissors.domain.model.rule.Move;

import java.util.Random;

public class ComputerPlayer extends Player {

    @Override
    public Move getMove() {
        return Move.values()[new Random().nextInt(3)];
    }
}
