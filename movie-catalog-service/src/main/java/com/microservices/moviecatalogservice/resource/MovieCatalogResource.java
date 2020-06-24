package com.microservices.moviecatalogservice.resource;

import com.microservices.moviecatalogservice.model.CatalogItem;
import com.microservices.moviecatalogservice.model.Movie;
import com.microservices.moviecatalogservice.model.Rating;
import com.microservices.moviecatalogservice.model.UserRating;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

  @Autowired
  private RestTemplate restTemplate;

  /*@Autowired
  WebClient.Builder webClientBuilder;*/

  @GetMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

    //Rating ratingObject = restTemplate.getForObject("http://localhost:8083/ratingsdata/" + ra, Rating.class);


    UserRating ratings = restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/1",
        UserRating.class);

    return ratings.getRatings().stream()
        .map(
            rating -> {
              Movie movieObject = restTemplate
                  .getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
             /* Movie movieObject = webClientBuilder.build()
                  .get()
                  .uri("http://localhost:8082/movies/" + rating.getMovieId())
                  .retrieve()
                  .bodyToMono(Movie.class)
                  .block();*/
              return new CatalogItem(movieObject.getName(), movieObject.getDescription(),
                  rating.getRating());
            })
        .collect(Collectors.toList());

    //return Collections.singletonList( new CatalogItem("Titanic", "Cathastrophic movie", 5));
  }
}
