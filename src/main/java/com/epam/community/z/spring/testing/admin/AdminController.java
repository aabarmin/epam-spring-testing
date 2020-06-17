package com.epam.community.z.spring.testing.admin;

import com.epam.community.z.spring.testing.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AdminController {
  private final PostService postService;

  @GetMapping("/admin")
  public ModelAndView showAllPosts(ModelAndView modelAndView) {
    modelAndView.addObject("posts", postService.findAll());
    modelAndView.setViewName("admin/posts");
    return modelAndView;
  }
}
