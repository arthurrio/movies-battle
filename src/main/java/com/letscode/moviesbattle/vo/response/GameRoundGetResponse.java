package com.letscode.moviesbattle.vo.response;

import com.letscode.moviesbattle.entity.GameRoundEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRoundGetResponse {

  private Long gameId;
  private Long round;
  private String card1;
  private String card2;
  private String status;
  private String message;

  public static GameRoundGetResponse from(GameRoundEntity gameRoundEntity, String card1, String card2) {

    return GameRoundGetResponse.builder().gameId(gameRoundEntity.getGameId())
        .round(gameRoundEntity.getRoundNumber())
        .card1(card1)
        .card2(card2)
        .build();
  }

}
