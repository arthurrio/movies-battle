package com.letscode.moviesbattle.service;

import java.time.LocalDateTime;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.util.MoviesBattleSpringBootTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;

@MoviesBattleSpringBootTest
public class GameServiceTest {

  public static final String PLAYER_TEST = "player_test";
  public static final long TOTAL_POINT_INITIAL_VALUE = 0L;

  @SpyBean
  private GameService gameService;

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameservice/insert-game-test.sql"})
  public void shouldFindAStartedGameByPlayer() {

    // arrange
    var expected = GameEntity.builder()
        .id(1L)
        .player(PLAYER_TEST)
        .start(LocalDateTime.of(2022,3,13,10,0))
        .end(null).build();

    // act

    var result = gameService.startGame(PLAYER_TEST);

    // assert
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getId(),expected.getId());
    Assertions.assertEquals(result.getPlayer(),expected.getPlayer());
    Assertions.assertEquals(result.getStart(),expected.getStart());
    Assertions.assertEquals(result.getEnd(),expected.getEnd());

  }

  @Test
  @Sql(scripts = {"classpath:sql/gameservice/insert-game-test-ended.sql"})
  public void shouldCreateNewGameWhenDontHaveAGameAlreadyStarted() {

    // arrange

    var expectedStart = LocalDateTime.of(2022,3,13,11,5);
    Mockito.when(gameService.currentTime()).thenReturn(expectedStart);

    var notExpected = GameEntity.builder()
        .id(1L)
        .player(PLAYER_TEST)
        .start(LocalDateTime.of(2022,3,13,10,5))
        .end(LocalDateTime.of(2022,3,13,10,10)).build();

    var expected =  GameEntity.builder()
        .id(2L)
        .player(PLAYER_TEST)
        .totalPoint(TOTAL_POINT_INITIAL_VALUE)
        .start(expectedStart)
        .end(null).build();


    // act
    var result = gameService.startGame(PLAYER_TEST);

    // assert
    Assertions.assertNotNull(result);
    Assertions.assertNotEquals(result.getId(),notExpected.getId());
    Assertions.assertNotEquals(result.getStart(),notExpected.getStart());
    Assertions.assertNotEquals(result.getEnd(),notExpected.getEnd());

    Assertions.assertEquals(result.getEnd(),expected.getEnd());
    Assertions.assertEquals(result.getTotalPoint(), expected.getTotalPoint());
    Assertions.assertEquals(result.getStart(),expected.getStart());

  }


}
