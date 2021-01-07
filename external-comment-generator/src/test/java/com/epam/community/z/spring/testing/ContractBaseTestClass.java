package com.epam.community.z.spring.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMessageVerifier
@Import(TestChannelBinderConfiguration.class)
public class ContractBaseTestClass {
  @Autowired
  private MessageVerifier verifier;

  @Autowired
  private StreamBridge streamBridge;

  @Autowired
  private CommentGenerator commentGenerator;

  @Test
  public void check_contextStarts() {
    assertNotNull(verifier);
    assertNotNull(streamBridge);
  }

  public void send() {
    streamBridge.send("commentSupplier-out-0", commentGenerator.next());
  }
}
