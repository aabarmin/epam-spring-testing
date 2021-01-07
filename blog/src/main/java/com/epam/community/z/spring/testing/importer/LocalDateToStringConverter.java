package com.epam.community.z.spring.testing.importer;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToStringConverter extends StdConverter<LocalDate, String> {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("y-MM-dd");

  @Override
  public String convert(LocalDate value) {
    return value.format(FORMATTER);
  }
}
