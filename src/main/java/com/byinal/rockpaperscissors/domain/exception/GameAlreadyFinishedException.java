package com.byinal.rockpaperscissors.domain.exception;

public class GameAlreadyFinishedException extends GameException {

    public GameAlreadyFinishedException(String message) {
        super(message);
    }
}
