package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.domain.model.player.ComputerPlayer;
import com.byinal.rockpaperscissors.domain.model.player.PersonPlayer;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.service.rule.RuleEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoundServiceTest {

    @Mock
    private RuleEngine defaultRuleEngine;

    private RoundService roundService;

    @BeforeEach
    void setUp() {
        roundService =new RoundService(defaultRuleEngine);
    }

    @Test
    public void should_evaluate_successfully_with_ruke_engine() {
        //given
        Player personPlayer = new PersonPlayer();
        Player computerPlayer = new ComputerPlayer();

        //when
        defaultRuleEngine.decide(personPlayer.getMove(), computerPlayer.getMove());

        //then
        verify(defaultRuleEngine).decide(personPlayer.getMove(), computerPlayer.getMove());
    }
}