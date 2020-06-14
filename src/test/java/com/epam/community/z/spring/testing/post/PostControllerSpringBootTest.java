package com.epam.community.z.spring.testing.post;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostControllerSpringBootTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @BeforeEach
  void save_shouldCreatePost() {
    final Post post = new Post();
    post.setContent("<p>Post content</p>");
    post.setComments(Collections.emptySet());

    final Map<String, Object> savedPost = restTemplate.postForObject("/posts", post, Map.class);

    assertNotNull(savedPost);
  }

  @Test
  void findAll_shouldReturnList() {
    final Post[] posts = restTemplate.getForObject("/posts", Post[].class);

    assertAll(
        () -> assertNotNull(posts),
        () -> assertTrue(posts.length > 0)
    );
  }
}