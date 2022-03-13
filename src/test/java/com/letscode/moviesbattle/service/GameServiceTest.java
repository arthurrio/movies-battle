package com.letscode.moviesbattle.service;

import java.time.LocalDateTime;

import com.letscode.moviesbattle.entity.GameEntity;
import com.letscode.moviesbattle.util.MoviesBattleSpringBootTest;
import com.letscode.moviesbattle.vo.response.GameDeleteResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;

@MoviesBattleSpringBootTest
public class GameServiceTest {

  public static final String PLAYER_TEST = "player_test";
  public static final String GAME_ENDED = "Jogo encerrado!";
  public static final String GAME_DONT_EXIST = "Não existe partida iniciada.";
  public static final long TOTAL_POINT_INITIAL_VALUE = 0L;
  public static final long GAME_ID = 1L;
  public static final long GAME_ID_2 = 2L;

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
        .id(GAME_ID)
        .player(PLAYER_TEST)
        .start(LocalDateTime.of(2022,3,13,10,5))
        .end(LocalDateTime.of(2022,3,13,10,10)).build();

    var expected =  GameEntity.builder()
        .id(GAME_ID_2)
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

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameservice/insert-game-test.sql"})
  public void shouldUpdateEndCollumnWhenGameEnd() {

    // arrange
    var expectedEnd = LocalDateTime.of(2022,3,13,11,5);
    Mockito.when(gameService.currentTime()).thenReturn(expectedEnd);

    var expected = GameDeleteResponse.builder()
        .gameId(GAME_ID)
        .player(PLAYER_TEST)
        .status(GAME_ENDED)
        .endGame(expectedEnd).build();

    // act
    var result = gameService.endGame(PLAYER_TEST);

    // assert
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getGameId(),expected.getGameId());
    Assertions.assertEquals(result.getPlayer(),expected.getPlayer());
    Assertions.assertEquals(result.getEndGame(),expected.getEndGame());
    Assertions.assertEquals(result.getStatus(),expected.getStatus());

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  public void shouldReturnErrorResponseWhenDontFindGame() {

    // arrange
    var expected = GameDeleteResponse.builder()
        .gameId(null)
        .player(null)
        .status(GAME_DONT_EXIST)
        .endGame(null).build();

    // act
    var result = gameService.endGame(PLAYER_TEST);

    // assert
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getGameId(),expected.getGameId());
    Assertions.assertEquals(result.getPlayer(),expected.getPlayer());
    Assertions.assertEquals(result.getEndGame(),expected.getEndGame());
    Assertions.assertEquals(result.getStatus(),expected.getStatus());

  }

}
