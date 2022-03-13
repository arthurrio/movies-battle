package com.letscode.moviesbattle.vo.response;

import java.time.LocalDateTime;

import com.letscode.moviesbattle.entity.GameEntity;

import lombok.Builder;
import lombok.Data;

import static com.letscode.moviesbattle.util.StatusMessage.GAME_ENDED;

@Data
@Builder
public class GameDeleteResponse {

  private Long gameId;
  private LocalDateTime endGame;
  private String player;
  private String status;

  public static GameDeleteResponse from(GameEntity game){
    return GameDeleteResponse.builder()
        .endGame(game.getEnd())
        .gameId(game.getId())
        .player(game.getPlayer())
        .status(GAME_ENDED)
        .build();
  }


}
