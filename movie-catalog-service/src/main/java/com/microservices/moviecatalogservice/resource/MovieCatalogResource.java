package com.microservices.moviecatalogservice.resource;

import com.microservices.moviecatalogservice.model.CatalogItem;
import com.microservices.moviecatalogservice.model.UserRating;
import com.microservices.moviecatalogservice.service.MovieInfo;
import com.microservices.moviecatalogservice.service.RatingInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


  @Autowired
  MovieInfo movieInfo;

  @Autowired
  RatingInfo ratingInfo;

  @GetMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") Long userId) {

    UserRating ratings = ratingInfo.getUserRating(userId);

    return ratings.getRatings().stream()
        .map(rating -> movieInfo.getCatalogItem(rating))
        .collect(Collectors.toList());
  }


}
