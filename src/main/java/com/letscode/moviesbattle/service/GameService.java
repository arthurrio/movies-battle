package com.letscode.moviesbattle.service;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepository;

  public Iterable<GameEntity> getAllGame() {
    return gameRepository.findAll();
  }

  public GameEntity endGame(){
    return null;
  }



}
