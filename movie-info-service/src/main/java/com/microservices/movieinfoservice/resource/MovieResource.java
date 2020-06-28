package com.microservices.movieinfoservice.resource;

import com.microservices.movieinfoservice.model.Movie;
import com.microservices.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

  @Value("${api.key}")
  private String apiKey;

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/{movieId}")
  public Movie getMovieInfo(@PathVariable("movieId") Long movieId) {
    MovieSummary movieSummary = restTemplate
        .getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey,
            MovieSummary.class);
    return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
  }

  @GetMapping("/test")
  public Movie getMovieInfo2() {
    return new Movie(1L, "Transformers", "Blabla bla");
  }

}
