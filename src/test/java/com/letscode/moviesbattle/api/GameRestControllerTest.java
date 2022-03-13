package com.letscode.moviesbattle.api;

import com.letscode.moviesbattle.util.MoviesBattleSpringBootTest;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

@MoviesBattleSpringBootTest
public class GameRestControllerTest {

  private GameRestController gameRestController;
  @LocalServerPort
  private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void shouldReturnForbiddenStatusWhenDontAuth() {
    given().get("/api/game").then().statusCode(HttpStatus.SC_UNAUTHORIZED);

  }

  @Test
  public void shouldReturnUnauthorizedStatusWhenWrongUser() {
    given().auth().basic("wronguser", "user")
        .get("/api/game")
        .then().statusCode(HttpStatus.SC_UNAUTHORIZED);

  }


  @Test
  public void shouldReturnUnauthorizedStatusWhenWrongPass() {
    given().auth().basic("user", "wrongpass")
        .get("/api/game")
        .then().statusCode(HttpStatus.SC_UNAUTHORIZED);

  }


  @Test
  public void shouldReturnOkStatusWhenAuthApprove() {
    given().auth().basic("user", "user")
        .get("/api/game")
        .then().statusCode(HttpStatus.SC_OK);
  }


}
