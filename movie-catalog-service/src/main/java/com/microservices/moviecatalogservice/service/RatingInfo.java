package com.microservices.moviecatalogservice.service;

import com.microservices.moviecatalogservice.model.Rating;
import com.microservices.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingInfo {

  @Autowired
  RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "getFallbackUserRating",
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
      })
  public UserRating getUserRating(
      @PathVariable("userId") Long userId) {
    return restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" + userId,
        UserRating.class);
  }

  public UserRating getFallbackUserRating(@PathVariable("userId") Long userId) {
    UserRating userRating = new UserRating();
    userRating.setUserId(userId);
    userRating.setRatings(Arrays.asList(new Rating("0", 0)));
    return userRating;
  }

}
