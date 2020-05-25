package com.example.forheart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * util class to parse datetime
 */
public class DateTimeUtils {

    public static String parseDateTimeFormat(String input) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("d M HH mm");
        SimpleDateFormat toFormat = new SimpleDateFormat("d MMM hh:mm a");
        try {
            String reformattedStr = toFormat.format(fromFormat.parse(input));
            return reformattedStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Null";
    }
}
