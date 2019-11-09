package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;

public interface GameManager {

    GameResponse startGame(GameStartRequest gameStartRequest);

    GameResponse retrieveGame(String id);

    GamePlayResponse play(String gameId, PlayRequest playRequest);
}
