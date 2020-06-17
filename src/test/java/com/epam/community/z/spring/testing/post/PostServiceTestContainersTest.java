package com.epam.community.z.spring.testing.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.community.z.spring.testing.comment.CommentValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringJUnitConfig(classes = {
    PostService.class,
    CommentValidator.class,
    PostSanitizer.class
})
@EnableAutoConfiguration
public class PostServiceTestContainersTest {
  @Container
  public static MySQLContainer DATABASE_CONTAINER = new MySQLContainer();

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", DATABASE_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", DATABASE_CONTAINER::getUsername);
    registry.add("spring.datasource.password", DATABASE_CONTAINER::getPassword);
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
  }

  @Autowired
  private PostService postService;

  @Test
  public void check_contextStarts() {
    assertThat(postService).isNotNull();
  }

  @Test
  public void save_createsRecord() {
    final Post post = new Post();
    post.setTitle("Post title");
    post.setContent("<p>Post content</p>");

    final Post savedPost = postService.save(post);

    assertThat(savedPost).isNotNull();

    final Post foundPost = postService.findOne(savedPost.getId());

    assertThat(foundPost).isNotNull();
  }
}