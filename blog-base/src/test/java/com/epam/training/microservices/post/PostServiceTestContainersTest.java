package com.epam.training.microservices.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.training.microservices.comment.CommentValidator;
import com.epam.training.microservices.post.PostServiceTestContainersTest.Initializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Disabled
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(classes = {
    PostService.class,
    CommentValidator.class,
    PostSanitizer.class
}, initializers = Initializer.class)
@EnableAutoConfiguration
public class PostServiceTestContainersTest {
  @Container
  public static MySQLContainer DATABASE_CONTAINER = new MySQLContainer();

  @Autowired
  private PostService unitUnderTest;

  @Test
  public void check_contextStarts() {
    assertThat(unitUnderTest).isNotNull();
  }

  @Test
  public void save_createsRecord() {
    final Post post = new Post();
    post.setTitle("Post title");
    post.setContent("<p>Post content</p>");

    final Post savedPost = unitUnderTest.save(post);

    assertThat(savedPost).isNotNull();

    final Post foundPost = unitUnderTest.findOne(savedPost.getId());

    assertThat(foundPost).isNotNull();
  }

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + DATABASE_CONTAINER.getJdbcUrl(),
          "spring.datasource.username=" + DATABASE_CONTAINER.getUsername(),
          "spring.datasource.password=" + DATABASE_CONTAINER.getPassword(),
          "spring.jpa.hibernate.ddl-auto=create-drop"
      ).applyTo(applicationContext.getEnvironment());
    }
  }
}