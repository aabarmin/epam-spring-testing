package com.epam.community.z.spring.testing.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping("")
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  public Post findOne(@PathVariable("id") int id) {
    return postService.findOne(id);
  }

  @PostMapping("")
  public Post save(@RequestBody Post post) {
    return postService.save(post);
  }
}
