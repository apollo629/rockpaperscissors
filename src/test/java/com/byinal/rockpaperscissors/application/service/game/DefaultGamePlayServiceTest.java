package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.model.request.PlayRequest;
import com.byinal.rockpaperscissors.application.service.round.RoundService;
import com.byinal.rockpaperscissors.domain.model.Move;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.game.GameVersusComputer;
import com.byinal.rockpaperscissors.domain.model.player.ComputerPlayer;
import com.byinal.rockpaperscissors.domain.model.player.PersonPlayer;
import com.byinal.rockpaperscissors.domain.model.round.Round;
import com.byinal.rockpaperscissors.domain.service.player.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultGamePlayServiceTest {

    @Mock
    private GameStatusService gameStatusService;

    @Mock
    private PlayerService playerService;

    @Mock
    private RoundService defaultRoundService;


    private DefaultGamePlayService defaultGamePlayService;

    @BeforeEach
    void setUp() {
        defaultGamePlayService = new DefaultGamePlayService(gameStatusService, playerService, defaultRoundService);
    }

    @Test
    void should_play_successfully() {
        //given
        PlayRequest playRequest = new PlayRequest();
        playRequest.setMove(Move.SCISSORS);
        Game game = new GameVersusComputer(3, new PersonPlayer(), new ComputerPlayer());
        Round round = mock(Round.class);

        when(defaultRoundService.evaluate(game.getFirstPlayer(), game.getSecondPlayer())).thenReturn(round);
        when(gameStatusService.updateStatus(game)).thenReturn(game);

        //when
        Game playedGame = defaultGamePlayService.play(game, playRequest);

        //then
        assertThat(playedGame.getRound()).isNotNull();
    }
}