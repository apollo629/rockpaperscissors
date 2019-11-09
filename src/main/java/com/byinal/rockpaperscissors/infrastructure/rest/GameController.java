package com.byinal.rockpaperscissors.infrastructure.rest;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.application.service.game.GameManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class GameController {

    private final GameManager gameManager;

    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @PostMapping("/games")
    @ResponseStatus(value = HttpStatus.CREATED)
    public GameResponse startGame(@RequestBody @Valid GameStartRequest gameStartRequest) {
        return gameManager.startGame(gameStartRequest);
    }

    @GetMapping("/games/{id}")
    public GameResponse startGame(@PathVariable String id) {
        return gameManager.retrieveGame(id);
    }

    @PostMapping("games/{gameId}/play")
    public GamePlayResponse play(@PathVariable String gameId, @RequestBody @Valid PlayRequest playRequest){
        return gameManager.play(gameId, playRequest);
    }
}
