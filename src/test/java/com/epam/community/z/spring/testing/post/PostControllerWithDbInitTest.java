package com.epam.community.z.spring.testing.post;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

@Sql(
    scripts = "/empty_posts.sql",
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(
    scripts = "/create_posts.sql",
    config = @SqlConfig(separator = ";")
)
@Sql(
    scripts = "/empty_posts.sql",
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostControllerWithDbInitTest {
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void findAll_shouldReturnPosts() {
    final int postCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "POSTS");

    assertTrue(postCount > 0);

    final Post[] posts = restTemplate.getForObject("/posts", Post[].class);

    assertAll(
        () -> assertNotNull(posts),
        () -> assertTrue(posts.length > 0)
    );
  }

  @Test
  @Sql("/create_special_post.sql")
  void findOne_shouldFindSpecialPost() {
    final Post post = restTemplate.getForObject("/posts/99", Post.class);

    assertAll(
        () -> assertNotNull(post),
        () -> assertEquals(99, post.getId())
    );
  }
}