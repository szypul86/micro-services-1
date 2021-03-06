package com.example.movieratingservice.resource;


import com.example.movieratingservice.model.Rating;
import com.example.movieratingservice.model.UserRating;
import java.util.Arrays;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

  @RequestMapping("/{movieId}")
  public Rating getRating(@PathVariable("movieId") String movieId) {
    return new Rating(movieId, 4);
  }

  @RequestMapping("/users/{userId}")
  public UserRating getRatingsByUser(@PathVariable("userId") Long userId) {
    UserRating ur = new UserRating();
    ur.setUserId(userId);
    ur.setRatings(Arrays.asList(
        new Rating("15", 4),
        new Rating("14", 3)));

    return ur;
  }


}