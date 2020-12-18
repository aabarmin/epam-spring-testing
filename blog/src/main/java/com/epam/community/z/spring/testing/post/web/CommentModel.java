package com.epam.community.z.spring.testing.post.web;

public class CommentModel {
  private String author;
  private String text;

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
}
