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
        optionalWinnerMove.ifPresent(winnerMove -> updatePlayerScores(p1, p2, winnerMove));
    }

    private void updatePlayerScores(Player firstPlayer, Player secondPlayer, Move winnerMove) {
        if (firstPlayer.getMove().equals(winnerMove)) {
            firstPlayer.incrementScore();
        } else {
            secondPlayer.incrementScore();
        }
    }
}
