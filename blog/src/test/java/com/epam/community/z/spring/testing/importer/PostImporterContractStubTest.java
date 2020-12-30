package com.epam.community.z.spring.testing.importer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {
    PostImporter.class,
    RestTemplate.class
})
@AutoConfigureStubRunner
class PostImporterContractStubTest {
  @Autowired
  private PostImporter importer;

  @Test
  void check_contextStarts() {
    assertNotNull(importer);
  }
}