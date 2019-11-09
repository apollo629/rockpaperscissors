package com.byinal.rockpaperscissors.application.converter;

import com.byinal.rockpaperscissors.application.model.dto.WinnerDto;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import org.springframework.stereotype.Component;

@Component
public class WinnerDtoConverter {

    public WinnerDto convert(Player winner) {
        WinnerDto winnerDto = new WinnerDto();
        winnerDto.setPlayerType(winner.getType());
        winnerDto.setScore(winner.getScore());
        return winnerDto;
    }

}
