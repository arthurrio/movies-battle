package com.letscode.moviesbattle.repository;

import com.letscode.moviesbattle.entity.GameEntity;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GameEntity, Long> {

  GameEntity findByEndIsNullAndPlayer(String player);

}
