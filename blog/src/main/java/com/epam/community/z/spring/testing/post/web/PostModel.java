package com.epam.community.z.spring.testing.post.web;

import java.util.HashSet;
import java.util.Set;

public class PostModel {
  private int id;
  private String title;
  private String content;
  private Set<CommentModel> comments = new HashSet<>();

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

  public Set<CommentModel> getComments() {
    return comments;
  }

  public void setComments(Set<CommentModel> comments) {
    this.comments = comments;
  }
}
