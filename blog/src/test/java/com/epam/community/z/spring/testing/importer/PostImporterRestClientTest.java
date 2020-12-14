package com.epam.community.z.spring.testing.importer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import com.epam.community.z.spring.testing.post.Post;
import java.time.LocalDate;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;


@RestClientTest(PostImporter.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@TestPropertySource(properties = {
    "importer.source.url=/importer"
})
public class PostImporterRestClientTest {
  @Autowired
  private PostImporter postImporter;

  @Autowired
  private MockRestServiceServer restServer;

  @Value("classpath:response_importer.json")
  private Resource importerResponse;

  @Test
  public void check_contextStarts() {
    assertAll(
        () -> assertNotNull(postImporter),
        () -> assertNotNull(restServer)
    );
  }

  @Test
  public void import_withMockRestServer() throws Exception {
    restServer.expect(requestTo("/importer"))
        .andRespond(withSuccess(importerResponse, MediaType.APPLICATION_JSON));

    final Collection<Post> importedPosts = postImporter
        .importPosts(LocalDate.now().minusMonths(1), LocalDate.now());

    assertAll(
        () -> assertNotNull(importedPosts),
        () -> assertEquals(2, importedPosts.size())
    );
  }
}