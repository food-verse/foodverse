package com.foodverse.utility.common;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

    private DateUtils() {}

    /**
     * Checks whether a given date falls within a specified range of dates.
     *
     * @param date the date to check
     * @param startDate the start date of the range (inclusive)
     * @param endDate the end date of the range (exclusive)
     */
    public static boolean isInRange(Date date, LocalDate startDate, LocalDate endDate) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.isAfter(startDate) || localDate.isBefore(endDate);
    }

}
