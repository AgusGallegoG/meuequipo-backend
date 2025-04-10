package com.web.meuequipo.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDates {

    public final static String shortFormat = "dd/MM/yyyy";

    public final static String timeFormat = "HH:mm:ss";

    public final static String longFormat = "dd/MM/yyyy HH:mm:ss";

    public static Date getNowDate() {
        return new Date();
    }

    public static String formatShortFormat(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(shortFormat).format(date);
    }

    public static String formatTimeFormat(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(timeFormat).format(date);
    }

    public static String formatLongFormat(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(longFormat).format(date);
    }
}
