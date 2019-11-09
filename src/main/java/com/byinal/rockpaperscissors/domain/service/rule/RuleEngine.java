package com.byinal.rockpaperscissors.domain.service.rule;

import com.byinal.rockpaperscissors.domain.model.Move;

import java.util.Optional;

public interface RuleEngine {

    Optional<Move> decide(Move move1, Move move2);

}
