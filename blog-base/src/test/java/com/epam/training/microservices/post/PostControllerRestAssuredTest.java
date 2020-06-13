package com.epam.training.microservices.post;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.epam.training.microservices.comment.CommentValidator;
import com.epam.training.microservices.comment.CommentValidatorConfiguration;
import io.restassured.http.ContentType;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    PostController.class,
    PostService.class,
    CommentValidatorConfiguration.class,
    CommentValidator.class,
    PostSanitizer.class
})
@EnableWebMvc
@WebAppConfiguration
public class PostControllerRestAssuredTest {
  @MockBean
  private PostRepository postRepository;

  @Autowired
  private WebApplicationContext webContext;

  @BeforeEach
  public void setUp() throws Exception {
    final Post post = new Post();
    post.setTitle("Post title");
    post.setContent("Post content");

    when(postRepository.findAll()).thenReturn(Collections.singletonList(post));
  }

  @Test
  public void findAll_returnsAllPosts() {
    given().
        standaloneSetup(MockMvcBuilders
            .webAppContextSetup(webContext)
            .alwaysDo(print())
        ).
    when().
        get("/posts").
    then().
        statusCode(200).
        contentType(ContentType.JSON).
        body("[0].title", equalTo("Post title"));
  }
}