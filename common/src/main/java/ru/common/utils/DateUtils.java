package ru.common.utils;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DateUtils {

    public DateUtils() {
    }

    public static LocalDateTime getLocalDateTimeMax(LocalDate date) {
        return Objects.nonNull(date)
                ? LocalDateTime.of(date, LocalTime.MAX).truncatedTo(ChronoUnit.MILLIS)
                : null;
    }

    public static LocalDateTime getLocalDateTimeMin(LocalDate date) {
        return Objects.nonNull(date) ? LocalDateTime.of(date, LocalTime.MIN) : null;
    }

    public static LocalDateTime getNowOfGreenwich() {
        return ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC-0")).toLocalDateTime();
    }
}