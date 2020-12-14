package com.epam.community.z.spring.testing.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Sql(
    scripts = "/empty_posts.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(
    scripts = "/create_posts.sql",
    config = @SqlConfig(separator = ";")
)
@JdbcTest
class PostJdbcTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void select_tables() {
    final List<Post> posts = jdbcTemplate.query("SELECT * FROM POSTS", (rs, rowNum) -> {
      final Post post = new Post();
      post.setId(rs.getInt("POST_ID"));
      post.setTitle(rs.getString("POST_TITLE"));
      post.setContent(rs.getString("POST_CONTENT"));
      return post;
    });

    assertAll(
        () -> assertNotNull(posts),
        () -> assertEquals(4, posts.size())
    );
  }

  @Test
  void check_contextStarts() {
    assertNotNull(jdbcTemplate);
  }
}