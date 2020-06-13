package com.epam.training.microservices.comment;

import com.epam.training.microservices.post.Comment;
import com.epam.training.microservices.post.Post;
import com.epam.training.microservices.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("posts/{id}")
@RequiredArgsConstructor
public class CommentController {
  private final PostService  postService;

  @PostMapping("/comments")
  public Post createComment(@PathVariable("id") int id, @RequestBody Comment comment) {
    return postService.addComment(
        postService.findOne(id),
        comment
    );
  }
}
