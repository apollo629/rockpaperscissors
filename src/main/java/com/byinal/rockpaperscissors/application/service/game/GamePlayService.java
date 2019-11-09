package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.domain.model.game.Game;

public interface GamePlayService {

    Game play(Game game, PlayRequest playRequest);

}
