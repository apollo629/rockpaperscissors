package com.byinal.rockpaperscissors.domain.model.game;

public enum GameType {

    COMPUTER;

    public boolean isGameVersusComputer(){
        return this.equals(GameType.COMPUTER);
    }

}
