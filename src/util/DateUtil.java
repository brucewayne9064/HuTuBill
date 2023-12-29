package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static long millisecondsOfOneDay = 1000*60*60*24;  //一天的毫秒数

    //把日期控件的时间转换成sql的时间
    public static java.sql.Date util2sql(java.util.Date d){
        return  new java.sql.Date(d.getTime());
    }

    public static Date today(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date monthBegin(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);  //设置为月初

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date monthEnd(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        c.set(Calendar.DATE ,1); //本月第一天
        c.add(Calendar.MONTH,1); //月份加一
        c.add(Calendar.DATE, -1); //天数减一
        return c.getTime();

    }

    public static int thisMonthTotalDay(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();
        return (int)((lastDayMilliSeconds - firstDayMilliSeconds)/millisecondsOfOneDay) + 1;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.thisMonthLeftDay());
    }

    public static int thisMonthLeftDay() {
        long lastDayMilliSeconds = monthEnd().getTime();
        long todayMillSeconds = today().getTime();
        return (int)((lastDayMilliSeconds - todayMillSeconds)/millisecondsOfOneDay) + 1;
    }
}
