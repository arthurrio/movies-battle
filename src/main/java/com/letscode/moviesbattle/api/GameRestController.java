package com.letscode.moviesbattle.api;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.service.GameRoundService;
import com.letscode.moviesbattle.service.GameService;
import com.letscode.moviesbattle.vo.request.GamePostRequest;
import com.letscode.moviesbattle.vo.response.GameDeleteResponse;

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

  private GameService gameService;
  private GameRoundService gameRoundService;

  @GetMapping()
  ResponseEntity<GameEntity> findAllByPlayer(Authentication authentication) {
    return ResponseEntity.ok(gameService.startGame(authentication.getName()));
  }

  @DeleteMapping
  ResponseEntity<GameDeleteResponse> endGame(Authentication authentication){
    return ResponseEntity.ok(gameService.endGame(authentication.getName()));
  }

  @GetMapping("/{gameId}/round")
  ResponseEntity<GameRoundEntity> getRound(@PathVariable("gameId") Long gameId ){
    return ResponseEntity.ok(gameRoundService.findGameRoundByGame(gameId));
  }

  @PostMapping("/{gameId}")
  ResponseEntity<GameRoundEntity> postOptionRound(@RequestBody GamePostRequest request, @PathVariable Long gameId){
    return ResponseEntity.ok(gameRoundService.submitOption(gameId, request.getOption()));
  }

}
