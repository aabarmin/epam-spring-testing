package com.epam.training.microservices.comment;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator {
  private final Collection<String> buzzwords;

  @Autowired
  public CommentValidator(@BuzzWords Collection<String> buzzwords) {
    this.buzzwords = buzzwords;
  }

  public boolean isValid(final String comment) {
    Preconditions.checkArgument(StringUtils.isNotBlank(comment), "Comment is blank");

    final String[] words = StringUtils.split(comment, " ");
    for (String word : words) {
      if (buzzwords.contains(word)) {
        return false;
      }
    }
    return true;
  }
}
