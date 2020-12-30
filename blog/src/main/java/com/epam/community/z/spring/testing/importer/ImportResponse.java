package com.epam.community.z.spring.testing.importer;

import com.epam.community.z.spring.testing.post.Post;

import java.util.Collection;

public class ImportResponse {
  private Collection<Post> posts;

  public ImportResponse() {
  }

  public ImportResponse(Collection<Post> posts) {
    this.posts = posts;
  }

  public Collection<Post> getPosts() {
    return posts;
  }

  public void setPosts(Collection<Post> posts) {
    this.posts = posts;
  }
}
