package com.microservices.moviecatalogservice.service;

import com.microservices.moviecatalogservice.model.CatalogItem;
import com.microservices.moviecatalogservice.model.Movie;
import com.microservices.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

  @Autowired
  private RestTemplate restTemplate;
  /*@Autowired
  WebClient.Builder webClientBuilder;*/

  @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
  public CatalogItem getCatalogItem(Rating rating) {
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
  }

  public CatalogItem getFallbackCatalogItem(Rating rating) {
    return (new CatalogItem("Movie name not found", "", 0));
  }

}
