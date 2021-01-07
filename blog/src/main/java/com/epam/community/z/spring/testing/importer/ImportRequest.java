package com.epam.community.z.spring.testing.importer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class ImportRequest {
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  private LocalDate startDate;

  @JsonSerialize(converter = LocalDateToStringConverter.class)
  private LocalDate endDate;

  public ImportRequest(LocalDate startDate, LocalDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
}
