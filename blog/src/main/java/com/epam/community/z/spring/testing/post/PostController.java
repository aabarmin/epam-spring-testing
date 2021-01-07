package com.epam.community.z.spring.testing.post;

import com.epam.community.z.spring.testing.post.web.PostMapper;
import com.epam.community.z.spring.testing.post.web.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
  @Autowired
  private PostService postService;

  @PostMapping("")
  public Post save(@RequestBody Post post) {
    return postService.save(post);
  }

  @GetMapping("")
  public List<PostModel> findAll() {
    return postService.findAll()
        .stream()
        .sorted(Comparator.comparing(p -> p.getComments().size(), Comparator.reverseOrder()))
        .map(post -> PostMapper.INSTANCE.toModel(post))
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public Post findOne(@PathVariable("id") int id) {
    return postService.findOne(id);
  }
}
