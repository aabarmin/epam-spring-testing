package com.epam.community.z.spring.testing.post;

import com.epam.community.z.spring.testing.comment.CommentValidator;
import com.epam.community.z.spring.testing.comment.InvalidCommentException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;
  private final CommentValidator commentValidator;
  private final PostSanitizer postSanitizer;

  public List<Post> findAll() {
    return postRepository.findAll();
  }

  public Post findOne(int id) {
    return postRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No post exception"));
  }

  public Post addComment(Post post, Comment comment) {
    if (!commentValidator.isValid(comment.getText())) {
      throw new InvalidCommentException();
    }
    post.getComments().add(comment);
    postRepository.save(post);
    return post;
  }

  public Post save(Post post) {
    post.setContent(postSanitizer.sanitize(post.getContent()));
    return postRepository.save(post);
  }
}
