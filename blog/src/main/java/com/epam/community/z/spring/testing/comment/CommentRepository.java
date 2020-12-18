package com.epam.community.z.spring.testing.comment;

import com.epam.community.z.spring.testing.post.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
