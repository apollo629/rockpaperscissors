package com.byinal.rockpaperscissors.infrastructure.rest;

import com.byinal.rockpaperscissors.application.model.request.GameStartRequest;
import com.byinal.rockpaperscissors.application.model.response.GamePlayResponse;
import com.byinal.rockpaperscissors.application.model.response.GameResponse;
import com.byinal.rockpaperscissors.application.model.response.Response;
import com.byinal.rockpaperscissors.application.model.response.ResponseStatus;
import com.byinal.rockpaperscissors.application.service.game.GameManager;
import com.byinal.rockpaperscissors.domain.model.game.GameType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIT {

    @Autowired
    private GameManager gameManager;

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int serverPort;

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void should_return_created_game_success_response_with_http_status_created() {
        //given
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/api/v1/games");

        GameStartRequest gameStartRequest = new GameStartRequest();
        gameStartRequest.setGameType(GameType.COMPUTER);
        gameStartRequest.setWinningScore(3);

        //when
        ResponseEntity<GameResponse> gameResponseResponseEntity = testRestTemplate.postForEntity(uriComponentsBuilder.build().encode().toUri(), new HttpEntity<>(gameStartRequest, headers), GameResponse.class);

        //then
        assertThat(gameResponseResponseEntity).isNotNull();
        assertThat(gameResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        GameResponse gameResponse = gameResponseResponseEntity.getBody();
        assertThat(gameResponse).isNotNull();
        assertThat(gameResponse.getStatus()).isEqualTo(ResponseStatus.SUCCESS.name());
        assertThat(gameResponse).extracting("winningScore", "gameType", "gameStatus")
                .contains(3, "COMPUTER", "IN_PROGRESS");
        assertThat(gameResponse.getId()).isNotNull();
    }


    @Test
    void should_return_failure_game_play_response_when_move_value_is_null() {
        //given
        ResponseEntity<GameResponse> gameResponseResponseEntity = createGame();
        assertThat(gameResponseResponseEntity).isNotNull();
        assertThat(gameResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        GameResponse gameResponse = gameResponseResponseEntity.getBody();
        assertThat(gameResponse).isNotNull();
        assertThat(gameResponse.getId()).isNotNull();

        String gameId = gameResponse.getId();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/api/v1/games/" + gameId + "/play");

        String playRequestInJSON = "{}";

        //when
        ResponseEntity<Response> responseEntity = testRestTemplate.postForEntity(uriComponentsBuilder.build().encode().toUri(), new HttpEntity<>(playRequestInJSON, headers), Response.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(ResponseStatus.FAILURE.name());
        assertThat(responseEntity.getBody().getErrorMessage()).isEqualTo("Move cannot be null");
    }


    @Test
    void should_return_failure_game_play_response_when_move_value_is_not_supported() {
        //given
        ResponseEntity<GameResponse> gameResponseResponseEntity = createGame();
        assertThat(gameResponseResponseEntity).isNotNull();
        assertThat(gameResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        GameResponse gameResponse = gameResponseResponseEntity.getBody();
        assertThat(gameResponse).isNotNull();
        assertThat(gameResponse.getId()).isNotNull();

        String gameId = gameResponse.getId();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/api/v1/games/" + gameId + "/play");

        String playRequestInJSON = "{\"move\":\"notSupportedMove\"}";

        //when
        ResponseEntity<Response> responseEntity = testRestTemplate.postForEntity(uriComponentsBuilder.build().encode().toUri(), new HttpEntity<>(playRequestInJSON, headers), Response.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(ResponseStatus.FAILURE.name());
    }

    @Test
    void should_return_success_game_play_response_when_move_value_is_supported() {
        //given
        ResponseEntity<GameResponse> gameResponseResponseEntity = createGame();
        assertThat(gameResponseResponseEntity).isNotNull();
        assertThat(gameResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        GameResponse gameResponse = gameResponseResponseEntity.getBody();
        assertThat(gameResponse).isNotNull();
        assertThat(gameResponse.getId()).isNotNull();

        String gameId = gameResponse.getId();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/api/v1/games/" + gameId + "/play");

        String playRequestInJSON = "{\"move\":\"ROCK\"}";

        //when
        ResponseEntity<GamePlayResponse> responseEntity = testRestTemplate.postForEntity(uriComponentsBuilder.build().encode().toUri(), new HttpEntity<>(playRequestInJSON, headers), GamePlayResponse.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        GamePlayResponse gamePlayResponse = responseEntity.getBody();
        assertThat(gamePlayResponse).isNotNull();
        assertThat(gamePlayResponse.getStatus()).isEqualTo(ResponseStatus.SUCCESS.name());
        assertThat(gamePlayResponse.getRoundDto().getRoundResult()).isNotNull();
        assertThat(gamePlayResponse.getPlayer1()).isNotNull();
        assertThat(gamePlayResponse.getPlayer2()).isNotNull();
    }

    private ResponseEntity<GameResponse> createGame() {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/api/v1/games");

        GameStartRequest gameStartRequest = new GameStartRequest();
        gameStartRequest.setGameType(GameType.COMPUTER);
        gameStartRequest.setWinningScore(3);

        //when
        return testRestTemplate.postForEntity(uriComponentsBuilder.build().encode().toUri(), new HttpEntity<>(gameStartRequest, null), GameResponse.class);
    }
}