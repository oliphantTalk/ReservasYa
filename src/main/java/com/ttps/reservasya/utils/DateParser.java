package com.ttps.reservasya.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parse(String date){
        if(StringUtils.isBlank(date)){
            return LocalDate.now();
        }
        return LocalDate.parse(date, YYYYMMDD);
    }
}
