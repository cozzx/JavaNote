package demo.date;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 常用的日期时间方法
 *
 * @author zt
 * @since 2023/7/25 07:09
 **/
public class DateTestCase {

    /**
     * 获取格式化当前时间
     */
    public String formatterNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

    @Test
    public void test1() {
        System.out.println(formatterNow());
    }

    /**
     * 解析字符串时间
     */
    public LocalDateTime parserNow(String dateTimeStr) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse(dateTimeStr));
    }

    @Test
    public void test2() {
        System.out.println(parserNow("2023-07-25 07:12:28"));
    }
}
