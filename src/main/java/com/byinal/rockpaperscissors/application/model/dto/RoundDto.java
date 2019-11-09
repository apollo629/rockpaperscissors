package com.byinal.rockpaperscissors.application.model.dto;

import com.byinal.rockpaperscissors.domain.model.round.RoundResult;

public class RoundDto {

    private RoundResult roundResult;
    private WinnerDto roundWinner;

    public RoundResult getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(RoundResult roundResult) {
        this.roundResult = roundResult;
    }

    public WinnerDto getRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(WinnerDto roundWinner) {
        this.roundWinner = roundWinner;
    }
}
