package com.epam.community.z.spring.test.service.sync.service;

import com.epam.community.z.spring.test.service.sync.model.Post;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
public class MockPostService implements PostService {
  @Value("classpath:posts.json")
  private Resource postsResource;

  @Autowired
  private ObjectReader objectReader;

  private List<Post> allPosts;

  @PostConstruct
  public void init() throws Exception {

  }

  @Override
  public List<Post> findAll() {
    return null;
  }

  @Override
  public List<Post> findAll(LocalDate createdStart, LocalDate createdEnd) {
    return null;
  }
}
