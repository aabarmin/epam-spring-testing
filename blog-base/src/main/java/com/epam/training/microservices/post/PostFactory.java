package com.epam.training.microservices.post;

import org.springframework.stereotype.Component;

@Component
public class PostFactory {
  private Post createEmptyPost() {
    final Post post = new Post();
    post.setContent("");
    return post;
  }

  public Post createNew() {
    final Post post = createEmptyPost();
    post.setTitle("New post");
    post.setComments(CommentUtils.generateEmptyComments());
    return post;
  }
}
