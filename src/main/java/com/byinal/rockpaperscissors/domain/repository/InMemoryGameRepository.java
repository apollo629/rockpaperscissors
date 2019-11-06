package com.byinal.rockpaperscissors.domain.repository;

import com.byinal.rockpaperscissors.domain.exception.GameNotFoundException;
import com.byinal.rockpaperscissors.domain.model.Game;
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
        String nextId = UUID.randomUUID().toString();
        gameMap.put(nextId, game);
        return nextId;
    }

    @Override
    public Game findById(String gameId) {
        return Optional.ofNullable(gameMap.get(gameId))
                .orElseThrow(GameNotFoundException::new);
    }
}
