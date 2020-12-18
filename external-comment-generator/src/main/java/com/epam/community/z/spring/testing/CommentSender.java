package com.epam.community.z.spring.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CommentSender {
  @Autowired
  @Output(Source.OUTPUT)
  private MessageChannel messageChannel;

  public void send(Comment comment) {
    final Message<Comment> message = MessageBuilder.withPayload(comment)
        .build();
    messageChannel.send(message);
  }
}
