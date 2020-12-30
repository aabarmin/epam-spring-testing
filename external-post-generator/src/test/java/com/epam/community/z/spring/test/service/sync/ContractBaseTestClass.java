package com.epam.community.z.spring.test.service.sync;

import com.epam.community.z.spring.test.service.sync.export.PostGenerationController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class ContractBaseTestClass {
  @Autowired
  private PostGenerationController controller;

  @BeforeEach
  public void setup() {
    final StandaloneMockMvcBuilder mockMvcBuilder = MockMvcBuilders.standaloneSetup(controller);
    RestAssuredMockMvc.standaloneSetup(mockMvcBuilder);
  }
}
