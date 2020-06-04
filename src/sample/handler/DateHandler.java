package sample.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHandler {

    private final int DATE;
    private final int MONTH;
    private final int YEAR;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.y");


    public DateHandler() {
        Calendar calendar = Calendar.getInstance();
        this.DATE = calendar.get(Calendar.DAY_OF_MONTH);
        this.MONTH = calendar.get(Calendar.MONTH);
        this.YEAR = calendar.get(Calendar.YEAR);
    }

    public int getDATE() {
        return DATE;
    }

    public int getMONTH() {
        return MONTH;
    }

    public int getYEAR() {
        return YEAR;
    }

    public String getNowFullDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }

    public String getThreeDayAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }

    public String getWeekAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }

    public String getMonthAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }

    public String getThreeMonthAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }


    public String getSixMountAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }

    public String getYearAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return simpleDateFormat.format(calendar.getTimeInMillis());
    }

    public String getUnixDate() {
        return "01.01.1970";
    }
}
