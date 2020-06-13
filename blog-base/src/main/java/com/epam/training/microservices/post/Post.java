package com.epam.training.microservices.post;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {
  @Id
  @Column(name = "POST_ID")
  private int id;

  @Column(name = "POST_TITLE")
  private String title;

  @Column(name = "POST_CONTENT")
  private String content;

  @OneToMany(mappedBy = "post")
  private Set<Comment> comments;
}
