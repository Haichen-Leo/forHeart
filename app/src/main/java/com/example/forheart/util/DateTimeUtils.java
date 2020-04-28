package com.example.forheart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtils {

    public static String parseDateTimeFormat(String input) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("d M HH mm");
        SimpleDateFormat toFormat = new SimpleDateFormat("d MMM HH:mm");
        try {
            String reformattedStr = toFormat.format(fromFormat.parse(input));
            return reformattedStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Null";
    }
}