package test.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * jdk8之前的时间api
 *
 * @author zt
 * @date 2023/7/21
 **/
public class DateTest {

    @Test
    public void test1() {
        // 毫秒时间戳
        long time = System.currentTimeMillis();
        System.out.println(time);

        // 日期类，输出格式：Sat Jul 22 14:13:20 CST 2023
        Date date = new Date();
        System.out.println(date);

        // 日期类，输出毫秒时间戳，同 System.currentTimeMillis()
        long time2 = date.getTime();
        System.out.println(time2);

        // 获取指定时间戳的日期类
        Date date2 = new Date(1690006570011L);
        System.out.println(date2);

        // java long型最大值的时间
        long time3 = Long.MAX_VALUE;
        System.out.println(time3);
        Date date3 = new Date(time3);
        System.out.println(date3);
    }

    /**
     * 格式化时间 java.text.SimpleDateFormat
     */
    @Test
    public void test2() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String formatted = simpleDateFormat.format(date);
        System.out.println(formatted); // 2023/7/22 下午11:16

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formatted1 = simpleDateFormat1.format(date);
        System.out.println(formatted1); // 2023-07-22 23:16:16.587

        try {
            // 解析字符串的时间格式得到date对象
            Date parsed = simpleDateFormat1.parse(formatted1);
            System.out.println(parsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
