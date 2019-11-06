package com.byinal.rockpaperscissors.domain.repository;

import com.byinal.rockpaperscissors.domain.model.Game;

public interface GameRepository {

    String save(Game game);

    Game findById(String gameId);

}
