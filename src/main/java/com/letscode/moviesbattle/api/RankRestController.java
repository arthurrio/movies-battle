package com.letscode.moviesbattle.api;


import com.letscode.moviesbattle.entity.RankEntity;
import com.letscode.moviesbattle.repository.RankRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/rank")
@AllArgsConstructor
public class RankRestController {

  RankRepository rankRepository;

  @GetMapping()
  ResponseEntity<Iterable<RankEntity>> findAll() {
    return ResponseEntity.ok(rankRepository.findTop10ByOrderByTotalPointDesc());
  }

}
