package com.letscode.moviesbattle.service;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.transaction.Transactional;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepository;

  @Transactional
  public GameEntity startGame(String player) {

    final GameEntity game = gameRepository.findByEndIsNullAndPlayer(player);
    if(Objects.isNull(game)){

      return gameRepository.save(GameEntity.builder().player(player).totalPoint(0L).start(currentTime()).build());

    }

    return game;
  }

  public GameEntity endGame(){
    return null;
  }


  public LocalDateTime currentTime(){
    return LocalDateTime.now();
  }

}
