package com.letscode.moviesbattle.repository;

import com.letscode.moviesbattle.entity.MovieEntity;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {



}
