package com.jzs.arc.utils;

import org.joda.time.DateTime;

public class MarkUtils {

    public static String creatMarkNum() {
        String dateString = new DateTime().toString("MMddHHmmssSS");
        return dateString;
    }
}
