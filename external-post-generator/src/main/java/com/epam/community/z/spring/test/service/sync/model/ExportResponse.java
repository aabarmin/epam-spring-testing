package com.epam.community.z.spring.test.service.sync.model;

import java.util.Collection;

public class ExportResponse {
  private Collection<Post> posts;

  public ExportResponse(Collection<Post> posts) {
    this.posts = posts;
  }

  public Collection<Post> getPosts() {
    return posts;
  }

  public void setPosts(Collection<Post> posts) {
    this.posts = posts;
  }
}
