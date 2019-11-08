package com.byinal.rockpaperscissors.application.service.game;

import com.byinal.rockpaperscissors.domain.exception.GameAlreadyFinishedException;
import com.byinal.rockpaperscissors.domain.model.game.Game;
import com.byinal.rockpaperscissors.domain.model.game.GameStatus;
import com.byinal.rockpaperscissors.domain.model.game.GameVersusComputer;
import com.byinal.rockpaperscissors.domain.model.player.ComputerPlayer;
import com.byinal.rockpaperscissors.domain.model.player.PersonPlayer;
import com.byinal.rockpaperscissors.domain.model.player.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class GameStatusServiceTest {

    private GameStatusService gameStatusService = new GameStatusService();

    @Test
    void should_not_throw_exception_when_game_is_not_finished() {
        //given
        Game game = new GameVersusComputer(3, new PersonPlayer(), new ComputerPlayer());
        game.setGameStatus(GameStatus.IN_PROGRESS);

        //when
        Throwable throwable = catchThrowable(() -> gameStatusService.validateGameIsNotFinished(game));

        //then
        assertThat(throwable).isNull();
    }

    @Test
    void should_throw_exception_when_game_is_finished() {
        //given
        Game finishedGame = new GameVersusComputer(3, new PersonPlayer(), new ComputerPlayer());
        finishedGame.setGameStatus(GameStatus.FINISHED);

        //when
        Throwable throwable = catchThrowable(() -> gameStatusService.validateGameIsNotFinished(finishedGame));

        //then
        assertThat(throwable).isNotNull()
                .isInstanceOf(GameAlreadyFinishedException.class);

    }

    @Test
    void should_update_game_status_as_finished_when_first_player_score_is_equals_to_winning_score() {
        //given
        Integer winningScore = 3;
        Player firstPlayer = new PersonPlayer();
        firstPlayer.setScore(winningScore);
        Player secondPlayer = new PersonPlayer();
        Game game = new GameVersusComputer(winningScore, firstPlayer, secondPlayer);

        //when
        Game updatedGame = gameStatusService.updateStatus(game);

        //then
        assertThat(updatedGame.getGameStatus()).isEqualTo(GameStatus.FINISHED);
    }

    @Test
    void should_update_game_status_as_finished_when_second_player_score_is_equals_to_winning_score() {
        //given
        Integer winningScore = 3;
        Player firstPlayer = new PersonPlayer();
        Player secondPlayer = new PersonPlayer();
        secondPlayer.setScore(winningScore);
        Game game = new GameVersusComputer(winningScore, firstPlayer, secondPlayer);

        //when
        Game updatedGame = gameStatusService.updateStatus(game);

        //then
        assertThat(updatedGame.getGameStatus()).isEqualTo(GameStatus.FINISHED);
    }
}