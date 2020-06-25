package com.epam.community.z.spring.testing.post;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@JdbcTest
class PostJdbcTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void check_contextStarts() {
    assertNotNull(jdbcTemplate);
  }

  @Test
  void select_tables() {
    jdbcTemplate.execute("CREATE TABLE TEST");

    final List<Map<String, String>> tables = jdbcTemplate
        .query("SHOW TABLES", new RowMapper<Map<String, String>>() {
          @Override
          public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Map.of("title", rs.getString(1));
          }
        });

    assertAll(
        () -> assertNotNull(tables),
        () -> assertFalse(tables.isEmpty())
    );
  }
}