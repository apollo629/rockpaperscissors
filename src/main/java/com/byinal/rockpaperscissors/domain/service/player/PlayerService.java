package com.byinal.rockpaperscissors.domain.service.player;

import com.byinal.rockpaperscissors.domain.model.Move;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    public void updatePlayerMove(Player player, Move move){
        player.setMove(move);
    }

    public void updatePlayerScore(Optional<Player> player) {
        player.ifPresent(Player::incrementScore);
    }

}
