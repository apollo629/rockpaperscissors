package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.application.model.response.ResponseMapper;
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
class DefaultGameServiceTest {

    @Mock
    private GameFactory gameFactory;

    @Mock
    private GameRepository inMemoryGameRepository;

    @Mock
    private RoundService roundService;

    @Mock
    private GameStatusService gameStatusService;

    @Mock
    private ResponseMapper responseMapper;

    private DefaultGameService defaultGameService;

    @BeforeEach
    void setUp() {
        defaultGameService = new DefaultGameService(gameFactory, inMemoryGameRepository,
                roundService,
                gameStatusService,
                responseMapper);
    }

    @Test
    void should_start_game_succesfully() {
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
        defaultGameService.startGame(gameStartRequest);

        //then
        InOrder inOrder = inOrder(gameFactory, inMemoryGameRepository, responseMapper);
        inOrder.verify(gameFactory).createGame(gameStartRequest.getWinningScore(), gameStartRequest.getGameType());
        inOrder.verify(inMemoryGameRepository).save(gameVersusComputer);
        inOrder.verify(responseMapper).mapToGameResponse(gameVersusComputer);
        inOrder.verifyNoMoreInteractions();
    }
}