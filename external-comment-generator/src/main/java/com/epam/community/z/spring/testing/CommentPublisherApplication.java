package com.epam.community.z.spring.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

@SpringBootApplication
public class CommentPublisherApplication {
  @Autowired
  private CommentGenerator commentGenerator;

  @Bean
  public Supplier<Comment> commentSupplier() {
    return () -> commentGenerator.next();
  }

  public static void main(String[] args) {
    SpringApplication.run(CommentPublisherApplication.class, args);
  }
}
