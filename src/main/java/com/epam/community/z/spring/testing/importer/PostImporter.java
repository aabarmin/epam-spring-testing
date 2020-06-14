package com.epam.community.z.spring.testing.importer;

import com.epam.community.z.spring.testing.post.Post;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostImporter {
  @Value("${importer.source.url:http://other-host/importer}")
  private String importerAddress;

  @Autowired
  private RestTemplate restTemplate;

  public Collection<Post> importPosts(LocalDate startDate, LocalDate endDate) {
    final ImportResponse response = restTemplate
        .postForObject(importerAddress, new ImportRequest(startDate, endDate), ImportResponse.class);
    return response.getPosts();
  }
}
