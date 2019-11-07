package com.byinal.rockpaperscissors.domain.model.game;

public enum GameStatus {
    IN_PROGRESS, FINISHED;

    public boolean isFinished() {
        return this.equals(FINISHED);
    }
}
