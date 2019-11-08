package com.byinal.rockpaperscissors.domain.model.player;

import com.byinal.rockpaperscissors.domain.model.rule.Move;

public class PersonPlayer extends Player {

    public PersonPlayer() {
        this.type = PlayerType.PERSON;
    }

    @Override
    public Move move() {
        return this.getMove();
    }
}
