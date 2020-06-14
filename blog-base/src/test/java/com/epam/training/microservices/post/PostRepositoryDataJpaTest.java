package com.epam.training.microservices.post;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class PostRepositoryDataJpaTest {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  void check_contextStarts() {
    assertAll(
        () -> assertNotNull(postRepository),
        () -> assertNotNull(entityManager)
    );
  }

  @Test
  void findAll_shouldReadFromDatabase() {
    final Post post = new Post();
    post.setTitle("Post title");
    post.setContent("Post content");

    final Post persisted = entityManager.persist(post);

    final List<Post> posts = postRepository.findAll();

    assertAll(
        () -> assertNotNull(posts),
        () -> assertFalse(posts.isEmpty())
    );
  }
}