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
@Table(name = "MOVIE")
public class MovieEntity {

  @Id
  @GeneratedValue(generator = "id_movie_seq")
  @SequenceGenerator(name = "id_movie_seq", sequenceName = "id_movie_seq", allocationSize = 1)
  @Column(name = "id_movie")
  private Long id;

  @Column(name = "point")
  private Long point;

  @Column(name = "title")
  private String title;

  @Column(name = "imdb_rating")
  private Float imdbRating;

  @Column(name = "imdb_votes")
  private Long imdbVotes;

}
