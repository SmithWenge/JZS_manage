package com.jzs.arc.utils;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

public class ChecksToList {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private static Date time = new Date();

    public static String[] getString(String check) {
        String[] checks = check.split(",");
        return checks;
    }

    public static String[] getStringTwo(String check) {
        String[] checks = check.split(":");
        return checks;
    }
}
