package com.base.java.util.common;

import com.google.common.base.Strings;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.util.Date;

import static java.time.temporal.ChronoField.SECOND_OF_DAY;
public final class TimeUtil {
    private TimeUtil() {
    }

    private static final ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
    public static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    public static LocalDateTime toLocalDateTime(long unixTimestamp) {
        String timeValue = String.valueOf(unixTimestamp);
        if (timeValue.length() == 10) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp * 1000), ZoneId.systemDefault());
        } else if (timeValue.length() == 13) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp), ZoneId.systemDefault());
        }

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp * 1000), ZoneId.systemDefault());
    }

    public static long toUnixTimestamp(LocalDateTime dateTime) {
        return dateTime.toInstant(offset).toEpochMilli() / 1000;
    }


    public static String dateFormat(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static LocalDate toLocalDate(String value, String format) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(value, formatter);
    }

    public static LocalDateTime toLocalDateTime(String value, String format) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(value, formatter);
    }

    public static Date toDate(String value, String format) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static String format(TemporalAccessor time, String format) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(time);
    }


    /**
     * Useful DateTimeFormatters
     */

    public static class Formatters {
        private Formatters() {
        }

        public static final DateTimeFormatter LOG_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    }


    /**
     * Useful TemporalAdjusters
     */

    public static class Adjusters {
        private Adjusters() {
        }

        public static TemporalAdjuster firstSecondOfDay() {
            return temporal -> temporal.with(SECOND_OF_DAY, temporal.range(SECOND_OF_DAY).getMinimum());
        }

        public static TemporalAdjuster lastSecondOfDay() {
            return temporal -> temporal.with(SECOND_OF_DAY, temporal.range(SECOND_OF_DAY).getMaximum());
        }
    }
}
