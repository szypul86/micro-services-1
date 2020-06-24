package com.microservices.movieinfoservice.resource;

import com.microservices.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

  @GetMapping("/{movieId}")
  public Movie getMovieInfo(@PathVariable("movieId") Long movieId) {
    return new Movie(movieId, "Transformers", "Blabla bla");
  }

  @GetMapping("/test")
  public Movie getMovieInfo2() {
    return new Movie(1L, "Transformers", "Blabla bla");
  }

}
