package com.letscode.moviesbattle.vo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRoundSubmitOptionPostResponse {

  private String message;

  public static GameRoundSubmitOptionPostResponse from(Integer option) {
    return GameRoundSubmitOptionPostResponse.builder().message("Opção " + option + " inválida.").build();
  }

  public static GameRoundSubmitOptionPostResponse from(boolean resposne) {

    return resposne ?
        GameRoundSubmitOptionPostResponse.builder().message("Você ganhou 3 pontos!").build() :
        GameRoundSubmitOptionPostResponse.builder().message("Você errou nessa rodada!").build();

  }
}
