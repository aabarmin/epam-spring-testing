package com.epam.training.microservices.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.training.microservices.comment.CommentValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        PostService.class,
        CommentValidator.class,
        PostSanitizer.class
})
public class PostServiceSpringTest {
  @Autowired
  private PostService unitUnderTest;

  @MockBean
  private PostRepository postRepository;

  @Test
  public void check_contextStarts() {
    assertThat(unitUnderTest).isNotNull();
  }

  @Test
  public void save_savesPost() {
    final Post post = new Post();
    post.setContent("<p>Content</p>");

    unitUnderTest.save(post);

    verify(postRepository, times(1)).save(any(Post.class));
  }
}