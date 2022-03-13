package com.letscode.moviesbattle.service;

import java.util.Objects;

import javax.transaction.Transactional;

import com.letscode.moviesbattle.entity.GameRoundEntity;
import com.letscode.moviesbattle.repository.GameRepository;
import com.letscode.moviesbattle.repository.GameRoundRepository;

import org.springframework.stereotype.Service;

@Service
public class GameRoundService {

  private GameRoundRepository gameRoundRepository;
  private GameRepository gameRepository;

  @Transactional
  public GameRoundEntity findGameRoundByGame(final Long gameId, final String player) {
    final var round = gameRoundRepository.findGameRoundEntityByPointIsNullAndGameIdAndPlayer(gameId, player);

    if (Objects.isNull(round)) {

      final var game = gameRepository.findById(gameId);

      if (game.isPresent() && player.equals(game.get().getPlayer())) {

        // Criar Round
        final var newGameRound =
            gameRoundRepository.save(GameRoundEntity.builder().gameId(gameId).player(player)
                .card2Id(1L)
                .card1Id(2L)
                .roundNumber(game.get().getTotalRound() + 1)
                .build());

        game.get().setTotalRound(newGameRound.getRoundNumber());

        return newGameRound;
      }

      return null;
    }

    return round;
  }

  public GameRoundEntity submitOption(Long gameId, String player, Long round, Integer option) {

    final var gameRound = gameRoundRepository.findGameRoundEntityByPlayerAndGameIdAndRoundNumber
        (player, gameId, round);

    gameRound.setAnswer(option);

    return gameRound;
  }
}
