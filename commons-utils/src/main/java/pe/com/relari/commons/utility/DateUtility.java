package pe.com.relari.commons.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * class: DateUtils
 * 
 * @author Relari
 */

public class DateUtility {

  private DateUtility() {}

  public static final String DATE_FORMAT = "dd/MM/yyyy";
  public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
  private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

  public static String formatDate(LocalDate date) {
    return date.format(DATE_FORMATTER);
  }

  public static LocalDate parseLocalDate(String date) {
    return LocalDate.parse(date, DATE_FORMATTER);
  }

  public static String formatDatetime(LocalDateTime date) {
    return date.format(DATETIME_FORMATTER);
  }

  public static LocalDateTime parseLocalDateTime(String date) {
    return LocalDateTime.parse(date, DATETIME_FORMATTER);
  }

  public static String buildDate(long millis) {
    return new SimpleDateFormat(DATETIME_FORMAT).format(new Date(millis));
  }

}
