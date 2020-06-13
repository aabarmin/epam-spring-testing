package com.epam.training.microservices.post;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import javax.annotation.PostConstruct;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PostSanitizer {
  private TransformerFactory transformerFactory;

  @PostConstruct
  public void init() throws Exception {
    transformerFactory = TransformerFactory.newInstance();
  }

  @SneakyThrows
  public String sanitize(@NonNull String content) {
    final StreamSource templateSource = new StreamSource(getClass().getResourceAsStream("/sanitizer.xsl"));
    final Transformer transformer = transformerFactory.newTransformer(templateSource);

    final StreamSource source = new StreamSource(new ByteArrayInputStream(content.getBytes()));
    final StringWriter writer = new StringWriter();
    final StreamResult result = new StreamResult(writer);
    transformer.transform(source, result);

    return writer.toString();
  }
}
