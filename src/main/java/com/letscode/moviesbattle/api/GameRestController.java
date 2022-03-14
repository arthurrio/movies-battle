package com.letscode.moviesbattle.api;

import java.util.Objects;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.repository.MovieRepository;
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

import static com.letscode.moviesbattle.service.GameRoundService.OPTION_1;
import static com.letscode.moviesbattle.service.GameRoundService.OPTION_2;

@RestController
@RequestMapping(path = "/api/game")
@AllArgsConstructor
public class GameRestController {

  public static final int ZERO_POINTS = 0;

  private GameService gameService;
  private GameRoundService gameRoundService;
  private MovieRepository movieRepository;

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

    final var title1 = movieRepository.findById(gameRound.getMovie1Id()).get().getTitle();
    final var title2 = movieRepository.findById(gameRound.getMovie2Id()).get().getTitle();


    return ResponseEntity.ok(GameRoundGetResponse.from(gameRound, title1, title2));
  }

  @PostMapping("/{gameId}/round")
  ResponseEntity<GameRoundSubmitOptionPostResponse> postOptionRound(@RequestBody GamePostRequest request,
                                                                    @PathVariable("gameId") Long gameId,
                                                                    Authentication authentication) {

    if (Objects.nonNull(request.getOption()) && request.getOption() != OPTION_1 && request.getOption() != OPTION_2) {

      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(GameRoundSubmitOptionPostResponse.from(request.getOption()));

    }

    final var gameRound = gameRoundService.submitOption(gameId, authentication.getName(), request.getOption());

    return ResponseEntity.ok(GameRoundSubmitOptionPostResponse.from(gameRound.getPoint() > ZERO_POINTS));
  }

}
