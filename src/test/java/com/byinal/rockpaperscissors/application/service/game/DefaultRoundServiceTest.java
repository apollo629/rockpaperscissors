package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.application.service.round.DefaultRoundService;
import com.byinal.rockpaperscissors.domain.model.Move;
import com.byinal.rockpaperscissors.domain.model.player.ComputerPlayer;
import com.byinal.rockpaperscissors.domain.model.player.PersonPlayer;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.round.Round;
import com.byinal.rockpaperscissors.domain.model.round.RoundResult;
import com.byinal.rockpaperscissors.domain.service.rule.RuleEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultRoundServiceTest {

    @Mock
    private RuleEngine defaultRuleEngine;

    private DefaultRoundService defaultRoundService;

    @BeforeEach
    void setUp() {
        defaultRoundService = new DefaultRoundService(defaultRuleEngine);
    }

    @Test
    public void should_return_draw_round_when_rule_engine_returns_empty_move() {
        //given
        Player personPlayer = mock(PersonPlayer.class);
        when(personPlayer.move()).thenReturn(Move.ROCK);
        when(personPlayer.getMove()).thenReturn(Move.ROCK);
        Player computerPlayer = mock(ComputerPlayer.class);
        when(computerPlayer.move()).thenReturn(Move.ROCK);
        when(computerPlayer.getMove()).thenReturn(Move.ROCK);

        when(defaultRuleEngine.decide(personPlayer.getMove(), computerPlayer.getMove())).thenReturn(Optional.empty());

        //when
        Round round = defaultRoundService.evaluate(personPlayer, computerPlayer);

        //then
        verify(defaultRuleEngine).decide(personPlayer.getMove(), computerPlayer.getMove());
        assertThat(round).isNotNull();
        assertThat(round.getRoundResult()).isEqualTo(RoundResult.DRAW);
    }

    @Test
    public void should_return_break_round_when_rule_engine_returns_first_players_move_as_winning_move() {
        //given
        Player personPlayer = mock(PersonPlayer.class);
        when(personPlayer.move()).thenReturn(Move.ROCK);
        when(personPlayer.getMove()).thenReturn(Move.ROCK);
        Player computerPlayer = mock(ComputerPlayer.class);
        when(computerPlayer.move()).thenReturn(Move.PAPER);
        when(computerPlayer.getMove()).thenReturn(Move.PAPER);


        when(defaultRuleEngine.decide(personPlayer.getMove(), computerPlayer.getMove())).thenReturn(Optional.of(Move.ROCK));

        //when
        Round round = defaultRoundService.evaluate(personPlayer, computerPlayer);

        //then
        verify(defaultRuleEngine).decide(personPlayer.getMove(), computerPlayer.getMove());
        assertThat(round).isNotNull();
        assertThat(round.getRoundResult()).isEqualTo(RoundResult.BREAK);
        assertThat(round.getWinner()).isEqualTo(Optional.of(personPlayer));
    }

    @Test
    public void should_return_break_round_when_rule_engine_returns_second_players_move_as_winning_move() {
        //given
        Player personPlayer = mock(PersonPlayer.class);
        when(personPlayer.move()).thenReturn(Move.PAPER);
        when(personPlayer.getMove()).thenReturn(Move.PAPER);
        Player computerPlayer = mock(ComputerPlayer.class);
        when(computerPlayer.move()).thenReturn(Move.SCISSORS);
        when(computerPlayer.getMove()).thenReturn(Move.SCISSORS);

        when(defaultRuleEngine.decide(personPlayer.getMove(), computerPlayer.getMove())).thenReturn(Optional.of(Move.SCISSORS));

        //when
        Round round = defaultRoundService.evaluate(personPlayer, computerPlayer);

        //then
        verify(defaultRuleEngine).decide(personPlayer.getMove(), computerPlayer.getMove());
        assertThat(round).isNotNull();
        assertThat(round.getRoundResult()).isEqualTo(RoundResult.BREAK);
        assertThat(round.getWinner()).isEqualTo(Optional.of(computerPlayer));
    }
}