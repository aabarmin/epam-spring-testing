package com.epam.community.z.spring.test.service.sync.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocalDateConverterTest {
  @InjectMocks
  private LocalDateConverter converter;

  @Test
  void check_contextStarts() {
    assertNotNull(converter);
  }

  @Test
  void convert_checkConversion() {
    final String result = converter.convert(LocalDate.now());

    final Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    final Matcher matcher = datePattern.matcher(result);

    assertTrue(matcher.matches());
  }
}