package com.draper.dboot.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author draper_hxy
 */
public class DateFormatUtil {

    public static final String YMD = "yyyy-MM-dd";

    public static final String HMS = "HH:mm:ss";

    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";

    public static String formatYmd(TemporalAccessor temporal) {
        return format(temporal, YMD);
    }

    public static String formatHms(LocalTime localTime) {
        return format(localTime, HMS);
    }

    public static String formatYmdhms(TemporalAccessor temporal) {
        return format(temporal, YMD_HMS);
    }

    public static LocalDateTime parseLocalDateTime(String time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time, formatter);
    }

    public static LocalDate parseLocalDate(String time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(time, formatter);
    }

    public static Date parse2Date(String time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return Date.from(LocalDate.parse(time, formatter)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String format(TemporalAccessor temporal, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(temporal);
    }

    public static String plusDay(String day) {
        LocalDate date = parseLocalDate(day, YMD);
        return formatYmd(date.plusDays(1));
    }

    public static String minusDay(String day) {
        LocalDate date = parseLocalDate(day, YMD);
        return formatYmd(date.minusDays(1));
    }

    /**
     * 从一个时间点遍历到另外一个时间点，endDay 包括在内
     *
     * @param startDay        yyyy-MM-dd
     * @param endDay          yyyy-MM-dd
     * @param genericListener 遍历时的回调函数
     * @throws Exception
     */
    public void dateForeach(String startDay, String endDay, GenericListener genericListener) throws Exception {
        if (parseLocalDate(startDay, YMD).isAfter(parseLocalDate(endDay, YMD))) {
            throw new Exception("时间异常");
        }
        while (!startDay.equals(DateFormatUtil.plusDay(endDay))) {
            genericListener.callback(startDay);
            startDay = DateFormatUtil.plusDay(startDay);
        }
    }

}
