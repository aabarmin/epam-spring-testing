package com.epam.community.z.spring.testing.post;

import com.epam.community.z.spring.testing.comment.CommentValidator;
import com.epam.community.z.spring.testing.comment.InvalidCommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentValidator commentValidator;
  @Autowired
  private PostSanitizer postSanitizer;

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
