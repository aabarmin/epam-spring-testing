package com.epam.training.microservices.importer;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.status;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.training.microservices.post.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    PostImporterConfiguration.class,
    PostImporter.class
})
public class PostImporterTest {
  @Autowired
  private PostImporter unitUnderTest;

  @Autowired
  private ObjectMapper objectMapper;

  private WireMockServer wireMockServer;

  @BeforeEach
  public void setUp() throws Exception {
    wireMockServer = new WireMockServer(options().dynamicPort());
    wireMockServer.start();

    ReflectionTestUtils.setField(unitUnderTest, "importerPort", wireMockServer.port());
    WireMock.configureFor(wireMockServer.port());
  }

  @Test
  public void check_contextStarts() {
    assertThat(unitUnderTest).isNotNull();
  }

  @Test
  public void import_withWireMock() throws Exception {
    final ImportResponse response = new ImportResponse(Collections.singletonList(new Post()));
    final String responseBody = objectMapper.writeValueAsString(response);

    stubFor(
        post(urlEqualTo("/importer"))
            .withHeader("Accept", containing("application/json"))
            .willReturn(
                status(200)
                    .withFixedDelay(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(responseBody)
            )
    );

    final Collection<Post> posts = unitUnderTest
        .importPosts(LocalDate.now().minusMonths(1), LocalDate.now());

    assertThat(posts).isNotNull();
    assertThat(posts).hasSize(1);
  }
}