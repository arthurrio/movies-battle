package com.letscode.moviesbattle.repository;

import com.letscode.moviesbattle.entity.RankEntity;

import org.springframework.data.repository.CrudRepository;

public interface RankRepository extends CrudRepository<RankEntity,Long> {

  Iterable<RankEntity> findTop10ByOrderByTotalPointDesc();

}
