package com.epam.training.microservices.post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;

  @GetMapping("")
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  public Post findOne(@PathVariable("id") int id) {
    return postService.findOne(id);
  }

  @PostMapping("/")
  public Post save(@RequestBody Post post) {
    return postService.save(post);
  }
}
