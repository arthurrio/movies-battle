package com.letscode.moviesbattle.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class OmdbApiClientUtil {

  public static final String INVALID_RATE = "N / A";
  public static String QUERY_MODEL =
      "INSERT INTO MOVIE(id_movie,point,title,imdb_rating,imdb_votes) values (%s,%s,'%s',%s,%s);";

  public static void main(String[] args) throws JsonProcessingException {


    List<String> inserts = new ArrayList<>();
    int count = 70;
    for (int i = 1; i <= 4; i++) {

      RestTemplate restTemplate = new RestTemplate();

      String findListMovie
          = "https://www.omdbapi.com/?apikey=21e071d6&s=batman&type=movie&page=" + i;

      ResponseEntity<ResultResponseSearch> response
          = restTemplate.getForEntity(findListMovie.toLowerCase(), ResultResponseSearch.class);



      for (Object object :
          response.getBody().getSearch()) {


        String findMovie = "http://www.omdbapi.com/?apikey=21e071d6&plot=full&i=" + ((LinkedHashMap) object).get("imdbID");

        ResponseEntity<MovieJson> responseMovie
            = restTemplate.getForEntity(findMovie.toLowerCase(), MovieJson.class);

        final var responseMovieBody = responseMovie.getBody();

        assert responseMovieBody != null;
        if(!INVALID_RATE.equals(responseMovieBody.getImdbRating())) {
          inserts.add(String.format(QUERY_MODEL, i + ++count,
              Double.valueOf(responseMovieBody.getImdbRating()) * Long.parseLong(responseMovieBody.getImdbVotes().replace(",", "")),
              responseMovieBody.getTitle(),
              responseMovieBody.getImdbRating(),
              responseMovieBody.getImdbVotes().replace(",", "")));
        }
      }

    }

    System.out.println("\n\n\n\n");
    for (String insert:
         inserts) {
      System.out.println(insert);
    }
    System.out.println("\n\n\n\n");
  }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class ResultResponseSearch {

  @JsonProperty("Search")
  private Object[] search;
  @JsonProperty("totalResults")
  private String totalResults;

  @JsonProperty("Response")
  private String response;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class MovieJson {

  @JsonProperty("Title")
  String Title;
  @JsonProperty("imdbID")
  String imdbID;
  @JsonProperty("imdbRating")
  String imdbRating;
  @JsonProperty("imdbVotes")
  String imdbVotes;

}