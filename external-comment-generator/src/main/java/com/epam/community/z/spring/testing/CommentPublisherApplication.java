package com.epam.community.z.spring.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@EnableBinding(Source.class)
@SpringBootApplication
public class CommentPublisherApplication {
  @Autowired
  private CommentGenerator commentGenerator;

  @Autowired
  private CommentSender commentSender;

  @Scheduled(initialDelay = 500, fixedDelay = 1_000)
  public void generateComment() {
    final Comment comment = commentGenerator.next();
    commentSender.send(comment);
  }

  public static void main(String[] args) {
    SpringApplication.run(CommentPublisherApplication.class, args);
  }
}
