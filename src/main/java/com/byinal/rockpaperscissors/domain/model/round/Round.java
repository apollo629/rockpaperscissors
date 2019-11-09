package com.byinal.rockpaperscissors.domain.model.round;

import com.byinal.rockpaperscissors.domain.model.player.Player;

import java.util.Optional;

public class Round {

    private Player winner;
    private RoundResult roundResult;

    public static Round draw(){
        return new Round(RoundResult.DRAW);
    }

    public static Round winLose(Player winner){
        return new Round(RoundResult.BREAK, winner);
    }

    private Round(RoundResult roundResult){
        this.roundResult = roundResult;
    }

    private Round(RoundResult roundResult, Player winner){
        this.roundResult = roundResult;
        this.winner = winner;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public RoundResult getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(RoundResult roundResult) {
        this.roundResult = roundResult;
    }
}
