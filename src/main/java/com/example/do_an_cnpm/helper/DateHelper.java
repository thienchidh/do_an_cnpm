package com.example.do_an_cnpm.helper;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DateHelper {
    public Date getDayAfterToday() {
        final int amountDefault = 30;
        return getDayAfterToday(amountDefault);
    }

    public Date getDayAfterToday(int amount) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
}