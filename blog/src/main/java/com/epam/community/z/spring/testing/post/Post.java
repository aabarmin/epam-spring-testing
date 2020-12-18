package com.epam.community.z.spring.testing.post;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "POSTS")
public class Post {
  @Id
  @Column(name = "POST_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Size(min = 3, max = 1024)
  @Column(name = "POST_TITLE")
  private String title;

  @Size(min = 3, max = 4096)
  @Column(name = "POST_CONTENT")
  private String content;

  @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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
