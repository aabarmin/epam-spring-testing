package com.epam.community.z.spring.testing.admin;

import com.epam.community.z.spring.testing.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
  @Autowired
  private PostService postService;

  @GetMapping("/admin")
  public ModelAndView showAllPosts(ModelAndView modelAndView) {
    modelAndView.addObject("posts", postService.findAll());
    modelAndView.setViewName("admin/posts");
    return modelAndView;
  }
}
