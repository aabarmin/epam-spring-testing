package com.epam.training.microservices.importer;

import com.epam.training.microservices.post.Post;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportResponse {
  private Collection<Post> posts;
}
