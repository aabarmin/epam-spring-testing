package com.epam.training.microservices.comment;

import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentValidatorConfiguration {
  @Bean
  @BuzzWords
  public Collection<String> buzzWords(@Value("${buzz.words.list}") String wordString) {
    return Arrays.asList(
        StringUtils.split(wordString, ",")
    );
  }
}
