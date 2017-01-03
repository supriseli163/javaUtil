package com.base.java.util.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import static java.time.temporal.ChronoField.SECOND_OF_DAY;
public final class TimeUtil {
    private TimeUtil() {}

    private static final ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.now());

    public static LocalDateTime toLocalDateTime(long unixTimeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimeStamp * 1000), ZoneId.systemDefault());
    }

    public static long toUnixtTime(LocalDateTime dateTime) {
        return dateTime.toInstant(offset).toEpochMilli() / 1000;
    }

    public static class Formatters {
        private Formatters() {}

        public static final DateTimeFormatter LOG_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static class Adjusters {
        private Adjusters() {}

        public static TemporalAdjuster firstSecondOfDay() {
            return temporal -> temporal.with(SECOND_OF_DAY, temporal.range(SECOND_OF_DAY).getMaximum());
        }

        public static TemporalAdjuster lastSecondOfDay() {
            return temporal -> temporal.with(SECOND_OF_DAY, temporal.range(SECOND_OF_DAY).getMaximum());
        }
    }
}
