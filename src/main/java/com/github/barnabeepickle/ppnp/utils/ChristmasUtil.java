package com.github.barnabeepickle.ppnp.utils;

import java.util.Calendar;

public class ChristmasUtil {
    public static boolean isChristmas() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26;
    }

    public static boolean isDecember() {
        Calendar calender = Calendar.getInstance();

        return calender.get(Calendar.MONTH) == Calendar.DECEMBER;
    }
}
