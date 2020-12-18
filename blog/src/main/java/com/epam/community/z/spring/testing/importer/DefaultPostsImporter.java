package com.epam.community.z.spring.testing.importer;

import com.epam.community.z.spring.testing.post.Post;
import com.epam.community.z.spring.testing.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class DefaultPostsImporter implements ApplicationListener<ContextRefreshedEvent> {
  @Autowired
  private PostImporter postImporter;

  @Autowired
  private PostService postService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (postService.findAll().isEmpty()) {
      final Collection<Post> posts = postImporter.importPosts(
          LocalDate.now().minusWeeks(1),
          LocalDate.now()
      );
      posts.stream()
          .map(this::wrapContent)
          .forEach(postService::save);
    }
  }

  private Post wrapContent(Post post) {
    post.setContent(String.format(
        "<body>%s</body>",
        post.getContent()
    ));
    return post;
  }
}
