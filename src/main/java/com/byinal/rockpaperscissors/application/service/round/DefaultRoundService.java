package com.byinal.rockpaperscissors.application.service.round;

import com.byinal.rockpaperscissors.domain.model.Move;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import com.byinal.rockpaperscissors.domain.model.round.Round;
import com.byinal.rockpaperscissors.domain.service.rule.RuleEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultRoundService implements RoundService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRoundService.class);

    private final RuleEngine defaultRuleEngine;

    public DefaultRoundService(RuleEngine defaultRuleEngine) {
        this.defaultRuleEngine = defaultRuleEngine;
    }

    @Override
    public Round evaluate(Player p1, Player p2) {
        Optional<Move> optionalWinnerMove = defaultRuleEngine.decide(p1.move(), p2.move());
        Round round = optionalWinnerMove.map(winnerMove -> {
            Player winnerPlayer = getWinnerPlayer(p1, p2, winnerMove);
            return Round.winLose(winnerPlayer);
        }).orElse(Round.draw());
        logger.info("Round is finished as {}", round.getRoundResult());
        return round;
    }

    private Player getWinnerPlayer(Player firstPlayer, Player secondPlayer, Move winnerMove) {
        if (firstPlayer.getMove().equals(winnerMove)) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }
}
