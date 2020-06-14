package com.epam.training.microservices.post;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class PostJdbcTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void check_contextStarts() {
    assertNotNull(jdbcTemplate);
  }
}