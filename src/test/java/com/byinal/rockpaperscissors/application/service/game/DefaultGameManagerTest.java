package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.application.model.response.ResponseMapper;
import com.byinal.rockpaperscissors.domain.model.Move;
import com.byinal.rockpaperscissors.domain.model.game.GameFactory;
import com.byinal.rockpaperscissors.domain.model.game.GameType;
import com.byinal.rockpaperscissors.domain.model.game.GameVersusComputer;
import com.byinal.rockpaperscissors.domain.model.player.ComputerPlayer;
import com.byinal.rockpaperscissors.domain.model.player.PersonPlayer;
import com.byinal.rockpaperscissors.domain.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultGameManagerTest {

    @Mock
    private GameFactory gameFactory;

    @Mock
    private GameRepository inMemoryGameRepository;

    @Mock
    private DefaultGamePlayService defaultGamePlayService;

    @Mock
    private ResponseMapper responseMapper;

    private DefaultGameManager defaultGameManager;

    @BeforeEach
    void setUp() {
        defaultGameManager = new DefaultGameManager(gameFactory, inMemoryGameRepository,
                defaultGamePlayService, responseMapper);
    }

    @Test
    void should_start_game_successfully() {
        //given
        int winningScore = 3;
        GameStartRequest gameStartRequest = new GameStartRequest();
        gameStartRequest.setWinningScore(winningScore);
        gameStartRequest.setGameType(GameType.COMPUTER);

        GameVersusComputer gameVersusComputer = new GameVersusComputer(winningScore, new PersonPlayer(), new ComputerPlayer());
        gameVersusComputer.setId("uuid");
        when(gameFactory.createGame(gameStartRequest.getWinningScore(), gameStartRequest.getGameType())).thenReturn(gameVersusComputer);
        when(responseMapper.mapToGameResponse(gameVersusComputer)).thenReturn(new GameResponse());

        //when
        defaultGameManager.startGame(gameStartRequest);

        //then
        InOrder inOrder = inOrder(gameFactory, inMemoryGameRepository, responseMapper);
        inOrder.verify(gameFactory).createGame(gameStartRequest.getWinningScore(), gameStartRequest.getGameType());
        inOrder.verify(inMemoryGameRepository).save(gameVersusComputer);
        inOrder.verify(responseMapper).mapToGameResponse(gameVersusComputer);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_retrieve_game_successfully() {
        //given
        GameVersusComputer gameVersusComputer = new GameVersusComputer(3, new PersonPlayer(), new ComputerPlayer());
        gameVersusComputer.setId("uuid");

        when(inMemoryGameRepository.findById("uuid")).thenReturn(gameVersusComputer);
        when(responseMapper.mapToGameResponse(gameVersusComputer)).thenReturn(new GameResponse());

        //when
        defaultGameManager.retrieveGame("uuid");

        //then
        InOrder inOrder = inOrder(inMemoryGameRepository, responseMapper);
        inOrder.verify(inMemoryGameRepository).findById("uuid");
        inOrder.verify(responseMapper).mapToGameResponse(gameVersusComputer);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_play_game_successfully() {
        //given
        int winningScore = 3;
        PlayRequest playRequest = new PlayRequest();
        playRequest.setMove(Move.ROCK);

        GameVersusComputer gameVersusComputer = new GameVersusComputer(winningScore, new PersonPlayer(), new ComputerPlayer());
        gameVersusComputer.setId("uuid");

        when(inMemoryGameRepository.findById("uuid")).thenReturn(gameVersusComputer);
        when(defaultGamePlayService.play(gameVersusComputer, playRequest)).thenReturn(gameVersusComputer);
        when(responseMapper.mapToGamePlayResponse(gameVersusComputer)).thenReturn(new GamePlayResponse());

        //when
        defaultGameManager.play("uuid", playRequest);

        //then
        InOrder inOrder = inOrder(inMemoryGameRepository, defaultGamePlayService, responseMapper);
        inOrder.verify(inMemoryGameRepository).findById("uuid");
        inOrder.verify(defaultGamePlayService).play(gameVersusComputer, playRequest);
        inOrder.verify(responseMapper).mapToGamePlayResponse(gameVersusComputer);
        inOrder.verifyNoMoreInteractions();
    }
}