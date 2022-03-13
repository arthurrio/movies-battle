package com.letscode.moviesbattle.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
public class Home {

  @GetMapping
  @Hidden
  public RedirectView get(){
    return new RedirectView("/swagger-ui.html");
  }

}
