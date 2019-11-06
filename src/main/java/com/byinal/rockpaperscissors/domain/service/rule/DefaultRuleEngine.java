package com.byinal.rockpaperscissors.domain.service.rule;

import com.byinal.rockpaperscissors.domain.model.rule.Move;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.byinal.rockpaperscissors.domain.model.rule.Move.*;

@Component
public class DefaultRuleEngine implements RuleEngine {

    private static Map<Pair<Move,Move>, Move> ruleMap = new HashMap<>();

    public DefaultRuleEngine() {
        initializeRuleMap();
    }

    private void initializeRuleMap() {
        ruleMap.put(new Pair<>(ROCK, PAPER), PAPER);
        ruleMap.put(new Pair<>(ROCK, SCISSORS), ROCK);
        ruleMap.put(new Pair<>(ROCK, ROCK), null);
        ruleMap.put(new Pair<>(PAPER, ROCK), PAPER);
        ruleMap.put(new Pair<>(PAPER, SCISSORS), SCISSORS);
        ruleMap.put(new Pair<>(PAPER, PAPER), null);
        ruleMap.put(new Pair<>(SCISSORS, ROCK), ROCK);
        ruleMap.put(new Pair<>(SCISSORS, PAPER), SCISSORS);
        ruleMap.put(new Pair<>(SCISSORS, SCISSORS), null);
    }

    @Override
    public Optional<Move> decide(Move move1, Move move2) {
        return Optional.ofNullable(ruleMap.get(new Pair<>(move1, move2)));
    }
}
