package com.epam.training.microservices.post;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
class PostControllerWebMvcTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PostService postService;

  @Test
  void check_contextStarts() {
    assertNotNull(mockMvc);
  }

  @Test
  void findOne_shouldReturnThePost() throws Exception {
    final Post post = new Post();
    post.setId(10);
    post.setTitle("Post title");

    given(postService.findOne(10)).willReturn(post);

    mockMvc.perform(
        get("/posts/10")
    )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("title").value(is("Post title")))
        .andExpect(jsonPath("id").value(is(10)));
  }
}