package com.epam.community.z.spring.testing.importer;

import com.epam.community.z.spring.testing.post.Post;
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
