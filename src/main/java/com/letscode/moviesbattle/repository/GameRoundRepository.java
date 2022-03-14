package com.letscode.moviesbattle.repository;

import com.letscode.moviesbattle.entity.GameRoundEntity;

import org.springframework.data.repository.CrudRepository;

public interface GameRoundRepository extends CrudRepository<GameRoundEntity, Long> {

  GameRoundEntity findGameRoundEntityByPointIsNullAndGameIdAndPlayer(Long gameId, String player);

  GameRoundEntity findGameRoundEntityByPlayerAndGameIdAndRoundNumber(String player, Long gameId, Long RoundNumber);

  GameRoundEntity findGameRoundEntityByGameIdAndAndMovie1IdAndMovie2Id(Long gameId, Integer movie1, Integer movie2);

  /**
   * Criado para reteornar  o ultimo round mas foi substituido pela informação dentro da propria tabela Game
   *
   * @param gameId
   * @param player
   * @return
   */
  @Deprecated(forRemoval = true)
  GameRoundEntity findFirstByGameIdAndPlayerOrderByRoundNumberDesc(Long gameId, String player);

}
