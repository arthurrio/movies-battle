package com.letscode.moviesbattle.service;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.transaction.Transactional;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.repository.GameRepository;
import com.letscode.moviesbattle.vo.response.GameDeleteResponse;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import static com.letscode.moviesbattle.util.StatusMessage.GAME_DONT_EXIST;

@Service
@AllArgsConstructor
public class GameService {


  private GameRepository gameRepository;

  @Transactional
  public GameEntity startGame(String player) {

    final GameEntity game = gameRepository.findByEndIsNullAndPlayer(player);
    if(Objects.isNull(game)){

      return gameRepository.save(GameEntity.builder().player(player).totalPoint(0L).start(currentTime()).build());

    }

    return game;
  }

  @Transactional
  public GameDeleteResponse endGame(String player){

    final GameEntity game = gameRepository.findByEndIsNullAndPlayer(player);

    if(Objects.isNull(game)){

      return GameDeleteResponse.builder().status(GAME_DONT_EXIST).build();

    }

    game.setEnd(currentTime());

    return GameDeleteResponse.from(game);
  }


  public LocalDateTime currentTime(){
    return LocalDateTime.now();
  }

}
