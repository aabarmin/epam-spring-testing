package com.epam.training.microservices.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@JsonTest
class PostControllerJsonTest {
  @Autowired
  private JacksonTester<Post> json;

  @Value("classpath:example_post.json")
  private Resource postContent;

  @Test
  void serialize_checkSerialized() throws Exception {
    final Post post = new Post();
    post.setTitle("Post title");
    post.setContent("Post content");

    assertThat(json.write(post)).hasJsonPath("title");
    assertThat(json.write(post)).hasJsonPathStringValue("title", "Post title");
  }

  @Test
  void deserialize_checkDeserialize() throws Exception {
    final Post post = json.readObject(postContent);

    assertAll(
        () -> assertNotNull(post),
        () -> assertEquals("Post title", post.getTitle()),
        () -> assertEquals("Post content", post.getContent())
    );
  }
}