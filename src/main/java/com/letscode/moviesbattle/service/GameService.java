package com.letscode.moviesbattle.service;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepository;

  public GameEntity startGame(String player) {

    GameEntity game = gameRepository.findByEndIsNullAndPlayer(player);

    return game;
  }

  public GameEntity endGame(){
    return null;
  }



}
