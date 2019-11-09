package com.byinal.rockpaperscissors.application.converter;

import com.byinal.rockpaperscissors.application.model.dto.PlayerDto;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerDtoConverter {

    public PlayerDto convert(Player player){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setType(player.getType());
        playerDto.setLastMove(player.getMove());
        playerDto.setScore(player.getScore());
        return playerDto;
    }
}
