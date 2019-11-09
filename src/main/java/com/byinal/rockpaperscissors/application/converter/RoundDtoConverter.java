package com.byinal.rockpaperscissors.application.converter;

import com.byinal.rockpaperscissors.application.model.dto.RoundDto;
import com.byinal.rockpaperscissors.application.model.dto.WinnerDto;
import com.byinal.rockpaperscissors.domain.model.round.Round;
import org.springframework.stereotype.Component;

@Component
public class RoundDtoConverter {

    private final WinnerDtoConverter winnerDtoConverter;

    public RoundDtoConverter(WinnerDtoConverter winnerDtoConverter) {
        this.winnerDtoConverter = winnerDtoConverter;
    }

    public RoundDto convert(Round round) {
        RoundDto roundDto = new RoundDto();
        roundDto.setRoundResult(round.getRoundResult());
        round.getWinner().ifPresent(winner -> {
            WinnerDto winnerDto = winnerDtoConverter.convert(winner);
            roundDto.setRoundWinner(winnerDto);
        });
        return roundDto;
    }
}
