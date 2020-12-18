package com.epam.community.z.spring.testing.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "COMMENTS")
public class Comment {
  @Id
  @Column(name = "COMMENT_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Size(min = 3, max = 1024)
  @Column(name = "COMMENT_AUTHOR")
  private String author;

  @Size(min = 3, max = 4096)
  @Column(name = "COMMENT_TEXT")
  private String text;

  @ManyToOne
  @JoinColumn(name = "POST_ID")
  private Post post;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
