package com.letscode.moviesbattle.service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.entity.MovieEntity;
import com.letscode.moviesbattle.repository.GameRepository;
import com.letscode.moviesbattle.repository.GameRoundRepository;
import com.letscode.moviesbattle.repository.MovieRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameRoundService {

  public static final int OPTION_2 = 2;
  public static final int OPTION_1 = 1;
  public static final int MAX_MOVIE_ID = 114;
  public static final int RIGHT_ANSWER = 3;
  public static final int WRONG_ANSWER = 0;
  public static final int LIMIT_WRONG_ANSWER = 3;
  private final GameRoundRepository gameRoundRepository;
  private final GameRepository gameRepository;
  private final MovieRepository movieRepository;

  @Transactional
  public GameRoundEntity findGameRoundByGame(final Long gameId, final String player) {
    final var round = gameRoundRepository.findGameRoundEntityByPointIsNullAndGameIdAndPlayer(gameId, player);

    if (Objects.isNull(round)) {

      final var game = gameRepository.findByEndIsNullAndPlayerAndId(player,gameId);

      if (Objects.nonNull(game)) {

        MovieShuffle movieShuffle = shuffleMovie(gameId);


        // Criar Round
        final var newGameRound =
            gameRoundRepository.save(GameRoundEntity.builder().gameId(gameId).player(player)
                .movie2Id(movieShuffle.getMovie2().getId())
                .movie1Id(movieShuffle.getMovie1().getId())
                .roundNumber(game.getTotalRound() + 1)
                .build());

        game.setTotalRound(newGameRound.getRoundNumber());

        return newGameRound;
      }

      return null;
    }

    return round;
  }

  @Transactional
  public GameRoundEntity submitOption(Long gameId, String player, Integer option) {

    final var gameRound = this.findGameRoundByGame(gameId,player);


    if(Objects.isNull(gameRound))
      return null;

    if(Objects.nonNull(gameRound.getPoint()) && Objects.nonNull(gameRound.getAnswer()))
      return gameRound;



    gameRound.setAnswer(option);

    final var movie1 = movieRepository.findById(gameRound.getMovie1Id());
    final var movie2 = movieRepository.findById(gameRound.getMovie2Id());
    GameEntity game = gameRepository.findByEndIsNullAndPlayerAndId(player, gameId);

    if(movie1.get().getPoint()>movie2.get().getPoint()){

      if(OPTION_1 == option) {
        gameRound.setPoint(RIGHT_ANSWER);
        game.setTotalPoint(game.getTotalRound()+RIGHT_ANSWER);
      }else {
        gameRound.setPoint(WRONG_ANSWER);
        if(gameRoundRepository.findAllByGameIdAndPlayerAndAndPoint(gameId,player,WRONG_ANSWER).size()== LIMIT_WRONG_ANSWER){
          game.setEnd(LocalDateTime.now());
        }
      }
    }

    return gameRound;
  }


  public MovieShuffle shuffleMovie(Long gameId) {

    Random random = new Random();
    int movieId1, movieId2;

    Optional<MovieEntity> movie1;
    GameRoundEntity gameRoundCheck;
    GameRoundEntity gameRoundCheckReverse;
    Optional<MovieEntity> movie2;
    do {
      do {
        movieId1 = random.nextInt(MAX_MOVIE_ID);
        movie1 = movieRepository.findById(movieId1);
      } while (movie1.isEmpty());

      do {
        movieId2 = random.nextInt(MAX_MOVIE_ID);
        movie2 = movieRepository.findById(movieId2);
      } while (movie2.isEmpty() || movie2.get().equals(movie1.get()));

      gameRoundCheck = gameRoundRepository.findGameRoundEntityByGameIdAndAndMovie1IdAndMovie2Id(gameId, movieId1, movieId2);

      gameRoundCheckReverse
          = gameRoundRepository.findGameRoundEntityByGameIdAndAndMovie1IdAndMovie2Id(gameId, movieId2, movieId1);


    } while (Objects.nonNull(gameRoundCheck) || Objects.nonNull(gameRoundCheckReverse));

      return new MovieShuffle(movie1.get(), movie2.get());
  }

}

@AllArgsConstructor
@Getter
class MovieShuffle {

  private MovieEntity movie1;
  private MovieEntity movie2;

}