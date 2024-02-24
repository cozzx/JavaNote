package test.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日历类
 *
 * @author zt
 * @date 2023/7/22
 **/
public class CalendarTest {

    @Test
    public void test1() {
        Calendar c = Calendar.getInstance();
        System.out.println(c);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);
    }

    @Test
    public void test2() {
        TimeZone t = TimeZone.getTimeZone("America/Los_Angeles");
        Calendar c = Calendar.getInstance(t);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);
    }

    @Test
    public void test3() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        System.out.println("当前时间日设置为8后,时间是:" + calendar.getTime());

        calendar.add(Calendar.HOUR, 2);
        System.out.println("当前时间加2小时后,时间是:" + calendar.getTime());

        calendar.add(Calendar.MONTH, -2);
        System.out.println("当前日期减2个月后,时间是:" + calendar.getTime());
    }
}
