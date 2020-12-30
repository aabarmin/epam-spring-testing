package com.epam.community.z.spring.testing.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.community.z.spring.testing.comment.CommentRepository;
import com.epam.community.z.spring.testing.comment.CommentValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        PostService.class,
        CommentValidator.class,
        PostSanitizer.class
})
public class PostServiceSpringTest {
  @Autowired
  private PostService unitUnderTest;

  @MockBean(answer = Answers.RETURNS_DEFAULTS)
  private PostRepository postRepository;

  @MockBean
  private CommentRepository commentRepository;

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