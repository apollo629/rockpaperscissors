package com.byinal.rockpaperscissors.infrastructure.rest;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.application.service.game.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
    @ResponseStatus(value = HttpStatus.CREATED)
    public GameResponse startGame(@RequestBody @Valid GameStartRequest gameStartRequest) {
        return gameService.startGame(gameStartRequest);
    }

    @GetMapping("/games/{id}")
    public GameResponse startGame(@PathVariable String id) {
        return gameService.retrieveGame(id);
    }

    @PostMapping("games/{gameId}/play")
    public GamePlayResponse play(@PathVariable String gameId, @RequestBody PlayRequest playRequest){
        return gameService.play(gameId, playRequest);
    }
}
