package com.epam.community.z.spring.test.service.sync.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends StdConverter<LocalDate, String> {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("y-M-d");

  @Override
  public String convert(LocalDate value) {
    return FORMATTER.format(value);
  }
}
