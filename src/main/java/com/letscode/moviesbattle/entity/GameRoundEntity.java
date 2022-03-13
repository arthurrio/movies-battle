package com.letscode.moviesbattle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "GAME_ROUND")
public class GameRoundEntity {

  @Id
  @GeneratedValue(generator = "id_game_round_seq")
  @SequenceGenerator(name = "id_game_round_seq", sequenceName = "id_game_round_seq", allocationSize = 1)
  @Column(name = "id_round")
  private Long id;

  @Column(name = "id_game", nullable = false)
  private Long gameId;

  @Column(name = "player", nullable = false)
  private String player;

  @Column(name = "round_number", nullable = false)
  private Long roundNumber;

  @Column(name = "id_movie_1",nullable = false)
  private Integer movie1Id;

  @Column(name = "id_movie_2",nullable = false)
  private Integer movie2Id;

  @Column(name = "answer")
  private Integer answer;

  @Column(name = "point")
  private Integer point;

}
