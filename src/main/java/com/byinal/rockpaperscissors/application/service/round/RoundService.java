package com.byinal.rockpaperscissors.application.service.round;

import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.round.Round;

public interface RoundService {

    Round evaluate(Player p1, Player p2);

}
