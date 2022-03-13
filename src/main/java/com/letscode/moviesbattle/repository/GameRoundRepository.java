package com.letscode.moviesbattle.repository;

import com.letscode.moviesbattle.entity.GameRoundEntity;

import org.springframework.data.repository.CrudRepository;

public interface GameRoundRepository extends CrudRepository<GameRoundEntity, Long> {

  GameRoundEntity findGameRoundEntityByPointIsNullAndGameIdAndPlayer(Long gameId, String player);

  GameRoundEntity findFirstByGameIdAndPlayerOrderByRoundNumberDesc(Long gameId, String player);

}
