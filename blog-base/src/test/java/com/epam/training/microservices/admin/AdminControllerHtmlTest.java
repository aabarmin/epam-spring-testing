package com.epam.training.microservices.admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.epam.training.microservices.post.Post;
import com.epam.training.microservices.post.PostService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.w3c.dom.Node;

@WebMvcTest(AdminController.class)
public class AdminControllerHtmlTest {
  @MockBean
  private PostService postService;

  @Autowired
  private WebClient webClient;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void check_contextStarts() {
    assertThat(webClient).isNotNull();
  }

  @Test
  public void admin_checkViewAndModel() throws Exception {
    given(postService.findAll()).willReturn(Collections.singletonList(new Post()));

    mockMvc.perform(get("/admin"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/posts"))
        .andExpect(model().attributeExists("posts"))
        .andExpect(model().attribute("posts", iterableWithSize(1)));
  }

  @Test
  public void admin_checkContentWithHtmlUnit() throws Exception {
    final HtmlPage page = webClient.getPage("/admin");

    assertThat(page).isNotNull();

    final Node headerElement = page.getElementsByTagName("h1").item(0);

    assertThat(headerElement).isNotNull();
    assertThat(headerElement.getTextContent()).isEqualTo("Admin Page");
  }
}