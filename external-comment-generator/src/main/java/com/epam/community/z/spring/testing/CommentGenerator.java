package com.epam.community.z.spring.testing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentGenerator {
  @Value("classpath:/MOCK_DATA.json")
  private Resource mockComments;

  private int current = 0;
  private List<Comment> comments;

  @PostConstruct
  public void init() {
    final Gson gson = new Gson();
    try (final InputStreamReader reader = new InputStreamReader(mockComments.getInputStream())) {
      this.comments = gson.fromJson(reader, new TypeToken<ArrayList<Comment>>() {}.getType());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Comment next() {
    if (current > comments.size()) {
      current = 0;
    }
    return comments.get(current++);
  }
}
