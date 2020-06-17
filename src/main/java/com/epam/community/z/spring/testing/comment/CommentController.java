package com.epam.community.z.spring.testing.comment;

import com.epam.community.z.spring.testing.post.Comment;
import com.epam.community.z.spring.testing.post.Post;
import com.epam.community.z.spring.testing.post.PostService;
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
