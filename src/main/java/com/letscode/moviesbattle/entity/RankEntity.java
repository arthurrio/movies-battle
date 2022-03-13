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
@Table(name = "RANK")
public class RankEntity {

  @Id
  @GeneratedValue(generator = "id_rank_seq")
  @SequenceGenerator(name = "id_rank_seq", sequenceName = "id_rank_seq", allocationSize = 1)
  @Column(name = "id_rank")
  private Long id;

  @Column(name = "player", nullable = false)
  private String player;

  @Column(name = "total_point", nullable = false)
  private Long totalPoint;

}
