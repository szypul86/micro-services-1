package com.microservices.moviecatalogservice.model;

import java.util.List;

public class UserRating {

  private Long userId;

  private List<Rating> ratings;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(List<Rating> ratings) {
    this.ratings = ratings;
  }
}
