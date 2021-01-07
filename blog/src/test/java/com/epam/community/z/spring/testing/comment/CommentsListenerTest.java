package com.epam.community.z.spring.testing.comment;

import com.epam.community.z.spring.testing.post.Comment;
import com.epam.community.z.spring.testing.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CommentsListenerTest {
  @Autowired
  private InputDestination inputDestination;

  @MockBean
  private PostService postService;

  @Test
  void sendMessage_shouldBeProcessed() {
    final Comment comment = new Comment();
    inputDestination.send(MessageBuilder.withPayload(comment).build());

    verify(postService, times(1)).findAll();
  }

  @TestConfiguration
  @Import(TestChannelBinderConfiguration.class)
  static class CommentsListenerConfig {

  }
}