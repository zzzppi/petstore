package com.petstore.cloud.feedback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    /**
     * @param start include
     * @param end   include
     * @return
     */
    public static List<Date> daysBetween(Date start, Date end) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.add(Calendar.DATE, 1);
        List<Date> days = new ArrayList<>();
        while (startCalendar.before(endCalendar)) {
            days.add(startCalendar.getTime());
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }
}
