package com.letscode.moviesbattle.vo.response;

import java.time.LocalDateTime;

import com.letscode.moviesbattle.entity.GameEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDeleteResponse {

  private Long gameId;
  private LocalDateTime endGame;
  private String status;

  public GameDeleteResponse from(GameEntity game){
    return GameDeleteResponse.builder()
        .endGame(game.getEnd())
        .gameId(game.getId())
        .status("Game encerrado!")
        .build();
  }


}
