package com.epam.community.z.spring.test.service.sync.export;

import com.epam.community.z.spring.test.service.sync.model.ExportRequest;
import com.epam.community.z.spring.test.service.sync.model.ExportResponse;
import com.epam.community.z.spring.test.service.sync.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostExportController {
  @Autowired
  private PostService postService;

  @PostMapping("/posts/export")
  public ExportResponse exportPosts(ExportRequest request) {
    return Optional.of(postService.findAll(request.getStartDate(), request.getEndDate()))
        .map(ExportResponse::new)
        .get();
  }
}
