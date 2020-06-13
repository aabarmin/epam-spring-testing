package com.epam.training.microservices.post;

import java.util.Collections;
import java.util.Set;

/**
 * Anti-pattern.
 *
 * @deprecated don't use static methods if possible
 */
@Deprecated
public class CommentUtils {
  public static Set<Comment> generateEmptyComments() {
    return Collections.emptySet();
  }
}
