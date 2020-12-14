package com.epam.community.z.spring.testing.comment;

import com.epam.community.z.spring.testing.post.Comment;
import com.epam.community.z.spring.testing.post.Post;
import com.epam.community.z.spring.testing.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("posts/{id}")
public class CommentController {
  @Autowired
  private PostService  postService;

  @PostMapping("/comments")
  public Post createComment(@PathVariable("id") int id, @RequestBody Comment comment) {
    return postService.addComment(
        postService.findOne(id),
        comment
    );
  }
}
