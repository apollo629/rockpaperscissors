package com.byinal.rockpaperscissors.domain.model.game;

import com.byinal.rockpaperscissors.domain.model.player.ComputerPlayer;
import com.byinal.rockpaperscissors.domain.model.player.PersonPlayer;
import org.springframework.stereotype.Component;

@Component
public class GameFactory {

    public Game createGame(Integer winningScore, GameType gameType){
        if (gameType.isGameVersusComputer()){
            return new GameVersusComputer(winningScore, new PersonPlayer(), new ComputerPlayer());
        } else {
            throw new IllegalArgumentException();
        }
    }

}
