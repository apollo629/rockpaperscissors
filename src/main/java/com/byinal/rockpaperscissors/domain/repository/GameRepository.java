package com.byinal.rockpaperscissors.domain.repository;

import com.byinal.rockpaperscissors.domain.model.game.Game;

public interface GameRepository {

    String save(Game game);

    Game findById(String gameId);

}
