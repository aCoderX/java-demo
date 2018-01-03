package com.acoderx.demo.jdk.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by xudi on 2018/1/3.
 */
public class TimeDemo {
    public static void main(String[] args){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(LocalDate.parse("2017-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);


        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime);

        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm::ss")));

        Instant instant = Instant.now();
        System.out.println(instant);

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
        System.out.println(clock.millis());

    }
}
