package com.hari.todo.helper;

import org.springframework.expression.ParseException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class Helper {

    public static Date parseDate(LocalDateTime localDateTime) throws ParseException {
        System.out.println(localDateTime);
//        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
