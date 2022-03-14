package com.letscode.moviesbattle.service;

import java.util.List;

import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.util.MoviesBattleSpringBootTest;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@MoviesBattleSpringBootTest
public class GameRoundServiceTest {

  @Autowired
  private GameRoundService gameRoundService;

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-sample-gameround.sql"})
  public void shouldReturnGameRound() {

    // arrange
    final var expected = GameRoundEntity.builder()
        .id(2L)
        .gameId(5L)
        .movie1Id(5)
        .movie2Id(6)
        .player("test")
        .point(null)
        .roundNumber(1L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L,"test");

    // assert
    Assertions.assertEquals(expected,result);

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-three-sample-gameround.sql"})
  public void shouldReturnJustOneGameRoundWhenProcessAGameWithThreeRounds() {

    // arrange
    final var expected = GameRoundEntity.builder()
        .id(4L)
        .gameId(5L)
        .movie1Id(35)
        .movie2Id(36)
        .player("test")
        .point(null)
        .roundNumber(3L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L,"test");

    // assert
    Assertions.assertEquals(expected,result);

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-three-sample-gameround-for-shuffle.sql"})
  @Sql(scripts = {"classpath:sql/create-movies.sql"})
  public void shouldShuffleTheMoviesThenReturnANewGameRound() {

    // arrange
    final var expected = GameRoundEntity.builder()
        .id(1L)
        .gameId(5L)
//        .movie1Id(35)
//        .movie2Id(36)
        .player("test")
        .point(null)
        .roundNumber(4L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L,"test");

    // assert
    Assertions.assertEquals(expected.getGameId(),result.getGameId());
    Assertions.assertEquals(expected.getId(),result.getId());
    Assertions.assertEquals(expected.getPlayer(),result.getPlayer());
    Assertions.assertEquals(expected.getRoundNumber(),result.getRoundNumber());
    Assertions.assertEquals(expected.getPoint(),result.getPoint());
    Assertions.assertEquals(expected.getAnswer(),result.getAnswer());

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-three-sample-gameround-for-shuffle.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-movie-for-shuffle-stress.sql"})
  public void shouldShuffleTheMoviesWhenJustHaveOneOption() {

    // arrange
    List<Integer> expectedMovie = Lists.list(7,8);

    final var expected = GameRoundEntity.builder()
        .id(1L)
        .gameId(5L)
        .movie1Id(7)
        .movie2Id(8)
        .player("test")
        .point(null)
        .roundNumber(6L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L,"test");

    // assert
    Assertions.assertEquals(expected,result);
    Assertions.assertEquals(expected.getGameId(),result.getGameId());
    Assertions.assertEquals(expected.getId(),result.getId());
    Assertions.assertEquals(expected.getPlayer(),result.getPlayer());
    Assertions.assertEquals(expected.getRoundNumber(),result.getRoundNumber());
    Assertions.assertEquals(expected.getPoint(),result.getPoint());
    Assertions.assertEquals(expected.getAnswer(),result.getAnswer());
    Assertions.assertTrue(expectedMovie.contains(result.getMovie1Id()));
    Assertions.assertTrue(expectedMovie.contains(result.getMovie2Id()));

  }


}
