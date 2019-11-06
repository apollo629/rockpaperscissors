package com.byinal.rockpaperscissors.domain.model;

public enum GameType {

    COMPUTER;

    boolean isGameVersusComputer(){
        return this.equals(GameType.COMPUTER);
    }

}
