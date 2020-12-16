package com.epam.community.z.spring.test.service.sync.service;

import com.epam.community.z.spring.test.service.sync.model.Post;

import java.time.LocalDate;
import java.util.List;

public interface PostGenerationService {
  List<Post> findAll();

  List<Post> findAll(LocalDate createdStart, LocalDate createdEnd);
}
