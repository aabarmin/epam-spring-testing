package com.epam.community.z.spring.test.service.sync.service;

import com.epam.community.z.spring.test.service.sync.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MockPostGenerationService implements PostGenerationService {
  @Value("classpath:posts.json")
  private Resource postsResource;

  private Gson gson;

  @PostConstruct
  public void init() {
    gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> {
          final String dateString = json.getAsString();
          return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("M/d/y"));
        })
        .create();
  }

  @Override
  public List<Post> findAll() {
    try (Reader reader = new InputStreamReader(postsResource.getInputStream())) {
      return gson.fromJson(reader, new TypeToken<ArrayList<Post>>(){}.getType());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Post> findAll(LocalDate createdStart, LocalDate createdEnd) {
    try (Reader reader = new InputStreamReader(postsResource.getInputStream())) {
      return gson.fromJson(reader, new TypeToken<ArrayList<Post>>(){}.getType());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
