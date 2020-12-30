package com.epam.community.z.spring.test.service.sync.model;

import com.epam.community.z.spring.test.service.sync.converter.LocalDateConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Post {
  private int id;
  private String title;
  private String content;
  @JsonSerialize(converter = LocalDateConverter.class)
  private LocalDate created;

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
