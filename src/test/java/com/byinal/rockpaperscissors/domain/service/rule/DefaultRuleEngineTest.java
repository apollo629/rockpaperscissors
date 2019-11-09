package com.byinal.rockpaperscissors.domain.service.rule;


import com.byinal.rockpaperscissors.domain.model.Move;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultRuleEngineTest {

    private RuleEngine ruleEngine = new DefaultRuleEngine();

    @Test
    public void should_decide_paper_when_move1_is_rock_and_move2_is_paper(){
        //given
        Move move1 = Move.ROCK;
        Move move2 = Move.PAPER;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isPresent();
        assertThat(optionalResultMove.isPresent()).isTrue();
        Move resultMove = optionalResultMove.get();
        assertThat(resultMove).isEqualTo(Move.PAPER);
    }


    @Test
    public void should_decide_rock_when_move1_is_rock_and_move2_is_scissors(){
        //given
        Move move1 = Move.ROCK;
        Move move2 = Move.SCISSORS;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isPresent();
        Move resultMove = optionalResultMove.get();
        assertThat(resultMove).isEqualTo(Move.ROCK);
    }

    @Test
    public void should_decide_null_when_move1_and_move2_is_both_rock(){
        //given
        Move move1 = Move.ROCK;
        Move move2 = Move.ROCK;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isNotPresent();
    }

    @Test
    public void should_decide_paper_when_move1_is_paper_and_move2_is_rock(){
        //given
        Move move1 = Move.PAPER;
        Move move2 = Move.ROCK;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isPresent();
        assertThat(optionalResultMove.isPresent()).isTrue();
        Move resultMove = optionalResultMove.get();
        assertThat(resultMove).isEqualTo(Move.PAPER);
    }

    @Test
    public void should_decide_scissors_when_move1_is_paper_and_move2_is_scissors(){
        //given
        Move move1 = Move.PAPER;
        Move move2 = Move.SCISSORS;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isPresent();
        assertThat(optionalResultMove.isPresent()).isTrue();
        Move resultMove = optionalResultMove.get();
        assertThat(resultMove).isEqualTo(Move.SCISSORS);
    }

    @Test
    public void should_decide_null_when_move1_and_move2_is_both_paper(){
        //given
        Move move1 = Move.PAPER;
        Move move2 = Move.PAPER;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isNotPresent();
    }


    @Test
    public void should_decide_scissors_when_move1_is_scissors_and_move2_is_paper(){
        //given
        Move move1 = Move.SCISSORS;
        Move move2 = Move.PAPER;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isPresent();
        assertThat(optionalResultMove.isPresent()).isTrue();
        Move resultMove = optionalResultMove.get();
        assertThat(resultMove).isEqualTo(Move.SCISSORS);
    }

    @Test
    public void should_decide_rock_when_move1_is_scissors_and_move2_is_rock(){
        //given
        Move move1 = Move.SCISSORS;
        Move move2 = Move.ROCK;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isPresent();
        assertThat(optionalResultMove.isPresent()).isTrue();
        Move resultMove = optionalResultMove.get();
        assertThat(resultMove).isEqualTo(Move.ROCK);
    }

    @Test
    public void should_decide_null_when_move1_and_move2_is_both_scissors(){
        //given
        Move move1 = Move.SCISSORS;
        Move move2 = Move.SCISSORS;

        //when
        Optional<Move> optionalResultMove = ruleEngine.decide(move1, move2);

        //then
        assertThat(optionalResultMove).isNotPresent();
    }
}