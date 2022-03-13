package com.letscode.moviesbattle.service;

import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.repository.GameRoundRepository;

import org.springframework.stereotype.Service;

@Service
public class GameRoundService {

  private GameRoundRepository gameRoundRepository;

  public GameRoundEntity findGameRoundByGame(Long gameId){
    return gameRoundRepository.findGameRoundByPointIsNullAndGameId(gameId);
  }

  public GameRoundEntity submitOption(Long gameId, Integer option){

    return null;
  }
}
