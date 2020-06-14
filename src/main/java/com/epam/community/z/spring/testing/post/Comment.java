package com.epam.community.z.spring.testing.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {
  @Id
  @Column(name = "COMMENT_ID")
  private int id;

  @Column(name = "COMMENT_AUTHOR")
  private String author;

  @Column(name = "COMMENT_TEXT")
  private String text;

  @ManyToOne
  private Post post;
}
