package com.microservices.moviecatalogservice.model;

public class Movie {

  Long id;
  String Name;
  String Description;

  public Movie(Long id, String name, String description) {
    this.id = id;
    Name = name;
    Description = description;
  }

  public Movie() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }
}
