package com.byinal.rockpaperscissors.domain.repository;

import com.byinal.rockpaperscissors.domain.exception.GameNotFoundException;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryGameRepository implements GameRepository {

    private Map<String, Game> gameMap = new HashMap<>();

    @Override
    public String save(Game game) {
        String id = UUID.randomUUID().toString();
        game.setId(id);
        gameMap.put(id, game);
        return id;
    }

    @Override
    public Game findById(String gameId) {
        return Optional.ofNullable(gameMap.get(gameId))
                .orElseThrow(() -> new GameNotFoundException("Game can not be found with id: " + gameId));
    }
}
