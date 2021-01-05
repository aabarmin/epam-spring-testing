package com.epam.community.z.spring.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMessageVerifier
public class ContractBaseTestClass {
  @Autowired
  private MessageVerifier verifier;

  @Autowired
  private CommentGenerator commentGenerator;

  @Test
  public void check_contextStarts() {
    assertNotNull(verifier);
  }

  public void send() {
    final Comment comment = commentGenerator.next();
  }
}
