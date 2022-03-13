package com.letscode.moviesbattle.entity;

import java.time.LocalDateTime;

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
@Table(name = "GAME")
public class GameEntity {

  @Id
  @GeneratedValue(generator = "id_game_seq")
  @SequenceGenerator(name = "id_game_seq", sequenceName = "id_game_seq", allocationSize = 1)
  @Column(name = "id_game")
  private Long id;

  @Column(name = "player")
  private String player;


  @Column(name = "total_point", nullable = false)
  private Long totalPoint;

  @Column(name = "total_point", nullable = false)
  private Long totalRound;

  @Column(name = "dat_start", nullable = false)
  private LocalDateTime start;

  @Column(name = "dat_end")
  private LocalDateTime end;

}
