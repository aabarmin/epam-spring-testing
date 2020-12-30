package com.epam.community.z.spring.test.service.sync.export;

import com.epam.community.z.spring.test.service.sync.model.ExportRequest;
import com.epam.community.z.spring.test.service.sync.model.ExportResponse;
import com.epam.community.z.spring.test.service.sync.service.PostGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostGenerationController {
  @Autowired
  private PostGenerationService postGenerationService;

  @GetMapping("/posts")
  public ExportResponse exportAllPosts() {
    return Optional.of(postGenerationService.findAll())
        .map(ExportResponse::new)
        .get();
  }

  @PostMapping("/posts/export")
  public ExportResponse exportPosts(ExportRequest request) {
    return Optional.of(postGenerationService.findAll(request.getStartDate(), request.getEndDate()))
        .map(ExportResponse::new)
        .get();
  }
}
