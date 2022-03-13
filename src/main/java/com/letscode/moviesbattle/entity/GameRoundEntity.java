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
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "GAME_ROUND")
public class GameRoundEntity {

  @Id
  @GeneratedValue(generator = "id_game_round_seq")
  @SequenceGenerator(name = "id_game_round_seq", sequenceName = "id_game_round_seq", allocationSize = 1)
  @Column(name = "id_round")
  private Long id;

  @Column(name = "id_game")
  private Long gameId;

  @Column(name = "player")
  private String player;

  @Column(name = "round_number")
  private Long roundNumber;

  @Column(name = "id_card_1")
  private Long card1Id;

  @Column(name = "id_card_2")
  private Long card2Id;

  @Column(name = "point")
  private Integer point;

}
