package com.bank.dao;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestDate {
    public static void main(String[] args) {
        /*LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.parse("2018-04-25",df);
        System.out.println("endDate : "+endDate);

        System.out.println(today.until(endDate, ChronoUnit.DAYS));*/

        List listHasCup=new ArrayList();
        listHasCup.add("65584584");
        listHasCup.add("1234");
        listHasCup.add("1234");
        listHasCup.add("65584584");

        List listNoCup=new ArrayList(new HashSet(listHasCup));
        System.out.println(listNoCup.size());
    }
}
