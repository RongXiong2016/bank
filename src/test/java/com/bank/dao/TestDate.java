package com.bank.dao;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TestDate {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.parse("2018-04-25",df);
        System.out.println("endDate : "+endDate);

        System.out.println(today.until(endDate, ChronoUnit.DAYS));
    }
}
