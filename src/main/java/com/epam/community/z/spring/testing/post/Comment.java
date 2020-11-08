package com.epam.community.z.spring.testing.post;

import javax.persistence.*;

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
