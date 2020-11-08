package com.epam.community.z.spring.testing.post;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

@Component
public class PostSanitizer {
  @Value("classpath:/sanitizer.xsl")
  private Resource sanitizerStylesheet;

  private Transformer transformer;

  @PostConstruct
  public void init() throws Exception {
    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    transformer = transformerFactory.newTransformer(new StreamSource(sanitizerStylesheet.getInputStream()));
  }

  public String sanitize(@NonNull String content) {
    final StreamSource source = new StreamSource(new ByteArrayInputStream(content.getBytes()));
    final StringWriter writer = new StringWriter();
    final StreamResult result = new StreamResult(writer);
    transform(source, result);
    return writer.toString();
  }

  private void transform(StreamSource source, StreamResult result) {
    try {
      transformer.transform(source, result);
    } catch (TransformerException e) {
      throw new RuntimeException("There is an exception during transformation", e);
    }
  }
}
