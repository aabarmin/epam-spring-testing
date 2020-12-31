package com.epam.community.z.spring.testing.importer;

import com.epam.community.z.spring.testing.post.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {
    PostImporter.class,
    RestTemplate.class
})
@AutoConfigureStubRunner(
    stubsMode = StubRunnerProperties.StubsMode.LOCAL,
    generateStubs = true,
    classifier = "",
    failOnNoStubs = true,
    ids = {
        "com.epam.community.z:external-post-generator-contract"
    }
)
class PostImporterContractStubTest {
  @Autowired
  private PostImporter importer;

  @Autowired
  private StubFinder stubFinder;

  @BeforeEach
  public void init() {
    final String url = stubFinder.findStubUrl("external-post-generator-contract").toString() + "/posts/export";
    ReflectionTestUtils.setField(importer, "importerAddress", url);
  }

  @Test
  void check_contextStarts() {
    assertNotNull(importer);
  }

  @Test
  public void importer_sendsRequest() {
    final Collection<Post> posts = importer.importPosts(LocalDate.now().minusWeeks(1), LocalDate.now());

    assertNotNull(posts);
    assertFalse(posts.isEmpty());
  }
}