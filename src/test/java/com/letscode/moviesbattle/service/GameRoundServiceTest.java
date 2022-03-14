package com.letscode.moviesbattle.service;

import java.util.List;

import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.repository.GameRepository;
import com.letscode.moviesbattle.util.MoviesBattleSpringBootTest;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@MoviesBattleSpringBootTest
public class GameRoundServiceTest {

  public static final String TEST_PLAYER = "test";
  @Autowired
  private GameRoundService gameRoundService;
  @Autowired
  private GameRepository gameRepository;

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-sample-gameround.sql"})
  public void shouldReturnGameRound() {

    // arrange
    final var expected = GameRoundEntity.builder()
        .id(32L)
        .gameId(5L)
        .movie1Id(5)
        .movie2Id(6)
        .player(TEST_PLAYER)
        .point(null)
        .roundNumber(1L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L, TEST_PLAYER);

    // assert
    Assertions.assertEquals(expected, result);

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-three-sample-gameround.sql"})
  public void shouldReturnJustOneGameRoundWhenProcessAGameWithThreeRounds() {

    // arrange
    final var expected = GameRoundEntity.builder()
        .id(43L)
        .gameId(5L)
        .movie1Id(35)
        .movie2Id(36)
        .player(TEST_PLAYER)
        .point(null)
        .roundNumber(3L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L, TEST_PLAYER);

    // assert
    Assertions.assertEquals(expected, result);

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
        .player(TEST_PLAYER)
        .point(null)
        .roundNumber(4L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L, TEST_PLAYER);

    // assert
    Assertions.assertEquals(expected.getGameId(), result.getGameId());
    Assertions.assertEquals(expected.getPlayer(), result.getPlayer());
    Assertions.assertEquals(expected.getRoundNumber(), result.getRoundNumber());
    Assertions.assertEquals(expected.getPoint(), result.getPoint());
    Assertions.assertEquals(expected.getAnswer(), result.getAnswer());

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-three-sample-gameround-for-shuffle-stress.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-movie-for-shuffle-stress.sql"})
  public void shouldShuffleTheMoviesWhenJustHaveOneOption() {

    // arrange
    List<Integer> expectedMovie = Lists.list(7, 8);

    final var expected = GameRoundEntity.builder()
        .id(1L)
        .gameId(5L)
        .movie1Id(7)
        .movie2Id(8)
        .player(TEST_PLAYER)
        .point(null)
        .roundNumber(6L)
        .build();

    // act
    final var result = gameRoundService.findGameRoundByGame(5L, TEST_PLAYER);

    // assert
    Assertions.assertEquals(expected.getGameId(), result.getGameId());
    Assertions.assertEquals(expected.getPlayer(), result.getPlayer());
    Assertions.assertEquals(expected.getRoundNumber(), result.getRoundNumber());
    Assertions.assertEquals(expected.getPoint(), result.getPoint());
    Assertions.assertEquals(expected.getAnswer(), result.getAnswer());
    Assertions.assertTrue(expectedMovie.contains(result.getMovie1Id()));
    Assertions.assertTrue(expectedMovie.contains(result.getMovie2Id()));

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-sample-gameround-submit-option.sql"})
  public void shouldSubmitOptionThenReturnGameRoundResult() {

    // arrange
    final var expected = GameRoundEntity.builder()
        .id(1L)
        .gameId(5L)
        .movie1Id(10)
        .movie2Id(20)
        .player(TEST_PLAYER)
        .point(3)
        .roundNumber(3L)
        .answer(1)
        .build();

    // act
    final var result = gameRoundService.submitOption(5L, TEST_PLAYER, 3L, 1);

    // assert
    Assertions.assertEquals(expected.getGameId(), result.getGameId());
    Assertions.assertEquals(expected.getPlayer(), result.getPlayer());
    Assertions.assertEquals(expected.getRoundNumber(), result.getRoundNumber());
    Assertions.assertEquals(expected.getPoint(), result.getPoint());
    Assertions.assertEquals(expected.getMovie1Id(), result.getMovie1Id());
    Assertions.assertEquals(expected.getMovie2Id(), result.getMovie2Id());
    Assertions.assertEquals(expected.getAnswer(), result.getAnswer());

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-sample-gameround-submit-option.sql"})
  public void shouldSubmitOptionThenReturnGameRoundResultWithZeroPoints() {

    // arrange
    final var expected = GameRoundEntity.builder()
        .id(1L)
        .gameId(5L)
        .movie1Id(10)
        .movie2Id(20)
        .player(TEST_PLAYER)
        .point(0)
        .roundNumber(3L)
        .answer(2)
        .build();

    // act
    final var result = gameRoundService.submitOption(5L, TEST_PLAYER, 3L, 2);

    // assert
    Assertions.assertEquals(expected.getGameId(), result.getGameId());
    Assertions.assertEquals(expected.getPlayer(), result.getPlayer());
    Assertions.assertEquals(expected.getRoundNumber(), result.getRoundNumber());
    Assertions.assertEquals(expected.getPoint(), result.getPoint());
    Assertions.assertEquals(expected.getMovie1Id(), result.getMovie1Id());
    Assertions.assertEquals(expected.getMovie2Id(), result.getMovie2Id());
    Assertions.assertEquals(expected.getAnswer(), result.getAnswer());

  }

  @Test
  @Sql(scripts = {"classpath:sql/erase-data.sql"})
  @Sql(scripts = {"classpath:sql/gameroundservice/insert-sample-gameround-submit-option-three-wrong-answer.sql"})
  public void shouldSubmitOptionThenReturnEndGameFor3AnswerWrong() {

    // arrange
    final int expectedTotalPoints = 3;
    final var expected = GameRoundEntity.builder()
        .id(1L)
        .gameId(5L)
        .movie1Id(10)
        .movie2Id(20)
        .player(TEST_PLAYER)
        .point(0)
        .roundNumber(4L)
        .answer(2)
        .build();

    // act
    final var result = gameRoundService.submitOption(5L, TEST_PLAYER, 4L, 2);
    final var gameResult = gameRepository.findById(5L);

    // assert
    Assertions.assertEquals(expected.getGameId(), result.getGameId());
    Assertions.assertEquals(expected.getPlayer(), result.getPlayer());
    Assertions.assertEquals(expected.getRoundNumber(), result.getRoundNumber());
    Assertions.assertEquals(expected.getPoint(), result.getPoint());
    Assertions.assertEquals(expected.getMovie1Id(), result.getMovie1Id());
    Assertions.assertEquals(expected.getMovie2Id(), result.getMovie2Id());
    Assertions.assertEquals(expected.getAnswer(), result.getAnswer());

    Assertions.assertTrue(gameResult.isPresent());
    Assertions.assertNotNull(gameResult.get().getEnd());
    Assertions.assertEquals(expectedTotalPoints, gameResult.get().getTotalPoint());
    Assertions.assertEquals(expected.getRoundNumber(), gameResult.get().getTotalRound());

  }

}
