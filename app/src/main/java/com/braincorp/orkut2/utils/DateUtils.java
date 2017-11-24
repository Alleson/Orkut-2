package com.braincorp.orkut2.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class DateUtils {

    private DateUtils() { }

    public static int getAge(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar target = getCalendar(date);
        int age = now.get(YEAR) - target.get(YEAR);
        if (target.get(MONTH) > now.get(MONTH) ||
                (target.get(MONTH) == now.get(MONTH) && target.get(DATE) > now.get(DATE))) {
            age--;
        }
        return age;
    }

    public static String getFormattedDate(Date date) {
        DateFormat format = SimpleDateFormat.getDateInstance(DateFormat.SHORT,
                Locale.getDefault());
        return format.format(date);
    }

    private static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
