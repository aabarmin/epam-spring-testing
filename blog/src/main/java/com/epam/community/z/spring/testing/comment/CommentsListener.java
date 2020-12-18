package com.epam.community.z.spring.testing.comment;

import com.epam.community.z.spring.testing.post.Comment;
import com.epam.community.z.spring.testing.post.Post;
import com.epam.community.z.spring.testing.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
public class CommentsListener {
  @Autowired
  private PostService postService;

  @StreamListener(Sink.INPUT)
  public void handleNewComment(Message<Comment> message) {
    final Comment comment = message.getPayload();
    final Optional<Post> postOptional = postService.findAll()
        .stream()
        .sorted(Comparator.comparingInt(p -> p.getComments().size()))
        .findFirst();
    if (postOptional.isPresent()) {
      comment.setId(0);
      comment.setPost(postOptional.get());
      postService.addComment(postOptional.get(), comment);
    }
  }
}
