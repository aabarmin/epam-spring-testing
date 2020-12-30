package com.epam.community.z.spring.testing.post;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "POSTS")
public class Post {
  @Id
  @Column(name = "POST_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "POST_TITLE")
  private String title;

  @Column(name = "POST_CONTENT")
  private String content;

  @OneToMany(mappedBy = "post")
  private Set<Comment> comments;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }
}
