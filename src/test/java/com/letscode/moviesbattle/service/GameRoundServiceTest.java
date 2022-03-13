package com.letscode.moviesbattle.service;

import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.util.MoviesBattleSpringBootTest;

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
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-sample-gameoround.sql"})
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
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-three-sample-gameoround.sql"})
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


}
