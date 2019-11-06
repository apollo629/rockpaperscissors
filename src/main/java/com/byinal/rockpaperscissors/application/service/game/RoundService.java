package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.rule.Move;
import com.byinal.rockpaperscissors.domain.service.rule.RuleEngine;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundService {

    private final RuleEngine defaultRuleEngine;

    public RoundService(RuleEngine defaultRuleEngine) {
        this.defaultRuleEngine = defaultRuleEngine;
    }

    public void evaluate(Player p1, Player p2) {
        Optional<Move> optionalWinnerMove = defaultRuleEngine.decide(p1.move(), p2.move());
        if (optionalWinnerMove.isPresent()) {
            Move winnerMove = optionalWinnerMove.get();
            if (p1.getMove().equals(winnerMove)) {
                p1.setScore(p1.getScore() + 1);
            } else if (p2.getMove().equals(winnerMove)) {
                p2.setScore(p2.getScore() + 1);
            } else {
                //return tie round
            }
        }
    }

}
