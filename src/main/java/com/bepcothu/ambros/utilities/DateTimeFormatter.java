package com.bepcothu.ambros.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeFormatter {
  public static String dateFormatter(LocalDateTime dateTime) {
    return dateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
