package com.epam.community.z.spring.testing.importer;

import com.epam.community.z.spring.testing.post.Post;
import com.epam.community.z.spring.testing.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
public class ImporterController {
  @Autowired
  private PostImporter postImporter;

  @Autowired
  private PostService postService;

  @GetMapping("/posts/import")
  public String startImporting() {
    final Collection<Post> posts = postImporter.importPosts(
        LocalDate.now().minusWeeks(1),
        LocalDate.now()
    );
    posts.stream()
        .map(this::wrapContent)
        .forEach(postService::save);
    return "Done";
  }

  private Post wrapContent(Post post) {
    post.setContent(String.format(
        "<body>%s</body>",
        post.getContent()
    ));
    return post;
  }
}
