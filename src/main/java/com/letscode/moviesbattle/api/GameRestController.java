package com.letscode.moviesbattle.api;

import java.util.Objects;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.service.GameRoundService;
import com.letscode.moviesbattle.service.GameService;
import com.letscode.moviesbattle.vo.request.GamePostRequest;
import com.letscode.moviesbattle.vo.response.GameDeleteResponse;
import com.letscode.moviesbattle.vo.response.GameRoundGetResponse;
import com.letscode.moviesbattle.vo.response.GameRoundSubmitOptionPostResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/game")
@AllArgsConstructor
public class GameRestController {

  public static final int OPTION_2 = 2;
  public static final int OPTION_1 = 1;
  public static final int ZERO_POINTS = 0;

  private GameService gameService;
  private GameRoundService gameRoundService;

  @GetMapping()
  ResponseEntity<GameEntity> findAllByPlayer(Authentication authentication) {
    return ResponseEntity.ok(gameService.startGame(authentication.getName()));
  }

  @DeleteMapping
  ResponseEntity<GameDeleteResponse> endGame(Authentication authentication) {
    return ResponseEntity.ok(gameService.endGame(authentication.getName()));
  }

  @GetMapping("/{gameId}/round")
  ResponseEntity<GameRoundGetResponse> getRound(@PathVariable("gameId") Long gameId, Authentication authentication) {

    final var gameRound = gameRoundService.findGameRoundByGame(gameId, authentication.getName());

    if (Objects.isNull(gameRound)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GameRoundGetResponse.buildNotfound());
    }

    return ResponseEntity.ok(GameRoundGetResponse.from(gameRound, "Movie 1", "Movie 2"));
  }

  @PostMapping("/{gameId}/round/{round}")
  ResponseEntity<GameRoundSubmitOptionPostResponse> postOptionRound(@RequestBody GamePostRequest request,
                                                                    @PathVariable("gameId") Long gameId,
                                                                    @PathVariable("round") Long round,
                                                                    Authentication authentication) {

    if (Objects.nonNull(request.getOption()) || request.getOption() != OPTION_1 || request.getOption() != OPTION_2) {

      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(GameRoundSubmitOptionPostResponse.from(request.getOption()));

    }

    final var gameRound = gameRoundService.submitOption(gameId, authentication.getName(), round, request.getOption());

    return ResponseEntity.ok(GameRoundSubmitOptionPostResponse.from(gameRound.getPoint() > ZERO_POINTS));
  }

}
