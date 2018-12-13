package com.ttps.reservasya.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parse(String date){
        return LocalDate.parse(date, YYYYMMDD);
    }
}
