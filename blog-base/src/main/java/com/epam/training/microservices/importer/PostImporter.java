package com.epam.training.microservices.importer;

import com.epam.training.microservices.post.Post;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostImporter {
  @Value("${importer.source.host:localhost}")
  private String importerHost;

  @Value("${importer.source.port:8080}")
  private int importerPort;

  @Autowired
  private RestTemplate restTemplate;

  public Collection<Post> importPosts(LocalDate startDate, LocalDate endDate) {
    final ImportResponse response = restTemplate
        .postForObject(getImporterUrl(), new ImportRequest(startDate, endDate), ImportResponse.class);
    return response.getPosts();
  }

  private String getImporterUrl() {
    return "http://" + importerHost + ":" + importerPort + "/importer";
  }
}
