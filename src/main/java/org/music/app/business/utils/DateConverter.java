package org.music.app.business.utils;

import jakarta.enterprise.context.ApplicationScoped;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

@ApplicationScoped
public class DateConverter {
    public Date convertToDate(String date){
        int year = Integer.parseInt(date.substring(6,10));
        int mounth = Integer.parseInt(date.substring(3,5));
        int day = Integer.parseInt(date.substring(0,2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,mounth,day);
        return new Date(calendar.getTime().getTime());
    }

    public String convertToFormat(Date date){
        var formated = new SimpleDateFormat("dd/MM/yyyy");
        var formatConvert = formated.format(date);
        return formatConvert;
    }
}
