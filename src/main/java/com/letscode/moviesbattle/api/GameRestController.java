package com.letscode.moviesbattle.api;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.service.GameRoundService;
import com.letscode.moviesbattle.service.GameService;
import com.letscode.moviesbattle.vo.request.GamePostRequest;
import com.letscode.moviesbattle.vo.response.GamePostResponse;

import org.springframework.http.ResponseEntity;
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
  ResponseEntity<Iterable<GameEntity>> findAllByPlayer() {
    return ResponseEntity.ok(gameService.getAllGame());
  }

  @DeleteMapping
  ResponseEntity<GameEntity> endGame(){
    return ResponseEntity.ok(gameService.endGame());
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
