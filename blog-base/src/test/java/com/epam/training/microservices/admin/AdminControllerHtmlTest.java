package com.epam.training.microservices.admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.epam.training.microservices.comment.CommentController;
import com.epam.training.microservices.post.Post;
import com.epam.training.microservices.post.PostController;
import com.epam.training.microservices.post.PostRepository;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.w3c.dom.Node;

@EnableWebMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
public class AdminControllerHtmlTest {
  @MockBean
  private PostRepository postRepository;

  @Autowired
  private WebApplicationContext webContext;

  private MockMvc mockMvc;
  private WebClient webClient;

  @BeforeEach
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
        .alwaysDo(print())
        .build();

    webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
        .build();

    final Post post = new Post();
    post.setId(1);
    post.setContent("Post content");
    post.setTitle("Post title");
    post.setComments(Collections.emptySet());

    when(postRepository.findAll()).thenReturn(Collections.singletonList(post));
  }

  @Test
  public void check_contextStarts() {
    assertThat(webContext).isNotNull();
  }

  @Test
  public void admin_checkViewAndModel() throws Exception {
    mockMvc.perform(get("/admin"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/posts"))
        .andExpect(model().attributeExists("posts"))
        .andExpect(model().attribute("posts", iterableWithSize(1)));
  }

  @Test
  public void admin_checkContentWithHtmlUnit() throws Exception {
    final HtmlPage page = webClient.getPage("http://localhost/admin");

    assertThat(page).isNotNull();

    final Node headerElement = page.getElementsByTagName("h1").item(0);

    assertThat(headerElement).isNotNull();
    assertThat(headerElement.getTextContent()).isEqualTo("Admin Page");
  }

  @Configuration
  @ComponentScan(basePackageClasses = {
      AdminController.class,
      PostController.class,
      CommentController.class
  })
  public static class TestConfig {

  }
}