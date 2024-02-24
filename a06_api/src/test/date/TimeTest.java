package test.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * jdk8+使用的时间api
 *
 * @author zt
 * @date 2023/7/22
 **/
public class TimeTest {

    /**
     * 获取日期时间
     */
    @Test
    public void test1() {
        // 获取日期
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); // 2023-07-23

        // 获取时间
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime); // 14:40:30.788479

        // 获取日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); // 2023-07-23T14:40:30.788677

        // 根据指定日期创建日期对象
        LocalDate localDateSpec = LocalDate.of(2023, 7, 22);
        System.out.println(localDateSpec); // 2019-05-13
        // +1 day
        LocalDate localDatePlus = localDateSpec.plusDays(1);
        System.out.println(localDatePlus);
        // -1 day
        LocalDate localDateMinus = localDateSpec.minusMonths(1);
        System.out.println(localDateMinus);
    }

    /**
     * 格式化
     */
    @Test
    public void test2() {
        // 预定义的标准格式，如：ISO_LOCAL_DATE_TIME ISO_LOCAL_DATE ISO_LOCAL_TIME
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = localDateTimeFormatter.format(localDateTime);
        System.out.println(localDateTime); // 2023-07-23T15:18:06.673637
        System.out.println(str1); // 2023-07-23T15:18:06.673637

        DateTimeFormatter localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.now();
        String str2 = localDateFormatter.format(localDate);
        System.out.println(localDate); // 2023-07-23
        System.out.println(str2); // 2023-07-23

        DateTimeFormatter localTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localTime = LocalTime.now();
        String str3 = localTimeFormatter.format(localTime);
        System.out.println(localTime); // 15:38:50.183477
        System.out.println(str3); // 15:38:50.183477

        DateTimeFormatter patternFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String patternFormatStr = patternFormatter.format(localDateTime);
        System.out.println(patternFormatStr); // 2023-07-25 06:33:54
    }

    /**
     * 解析
     */
    @Test
    public void test3() {
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        TemporalAccessor parseDateTime = localDateTimeFormatter.parse("2023-07-23T15:18:06.673637");
        LocalDateTime localDateTime = LocalDateTime.from(parseDateTime);
        System.out.println(localDateTime);

        DateTimeFormatter localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        TemporalAccessor parseDate = localDateFormatter.parse("2023-07-23");
        LocalDate localDate = LocalDate.from(parseDate);
        System.out.println(localDate);

        DateTimeFormatter localTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        TemporalAccessor parseTime = localTimeFormatter.parse("21:02:14.673637");
        LocalTime localTime = LocalTime.from(parseTime);
        System.out.println(localTime);

        DateTimeFormatter patternFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TemporalAccessor accessor = patternFormatter.parse("2023-07-25 06:27:22");
        LocalDateTime patternLocalDateTime = LocalDateTime.from(accessor);
        System.out.println(patternLocalDateTime); //2022-12-04T21:05:42
    }

    /**
     * 瞬时
     */
    @Test
    public void test4() {
        Instant instant = Instant.now();
        System.out.println(instant); //2023-07-24T22:38:45.425362Z

        OffsetDateTime instant1 = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(instant1); //2023-07-25T06:38:45.425362+08:00

        Instant instant2 = Instant.ofEpochMilli(24123123312L);
        System.out.println(instant2); //1970-10-07T04:52:03.312Z

        long milliTime = instant.toEpochMilli();
        System.out.println(milliTime); //1690238390721
    }

    /**
     * 时区ID
     */
    @Test
    public void test5() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for (String availableZoneId : availableZoneIds) {
            System.out.println(availableZoneId);
        }
    }

    /**
     * 不同时区的时间 ZonedDateTime
     */
    @Test
    public void test6() {
        ZonedDateTime t1 = ZonedDateTime.now();
        System.out.println(t1); //2023-07-25T06:42:36.955189+08:00[Asia/Shanghai]

        ZonedDateTime t2 = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(t2); //2023-07-24T18:42:36.959473-04:00[America/New_York]
    }

    /**
     * 日期间隔 Period，用于计算两个“日期”间隔
     */
    @Test
    public void test7() {
        LocalDate t1 = LocalDate.now();
        LocalDate t2 = LocalDate.of(2018, 8, 8);
        Period between = Period.between(t1, t2);
        System.out.println(between); // P-4Y-11M-17D
        System.out.println("相差的年数：" + between.getYears()); // -4
        System.out.println("相差的月数：" + between.getMonths()); // -11
        System.out.println("相差的天数：" + between.getDays()); // -17
        System.out.println("相差的总月数：" + between.toTotalMonths()); // -59

        Period between2 = Period.between(t2, t1);
        System.out.println(between2); // P4Y11M17D
        System.out.println("相差的年数：" + between2.getYears()); // 4
        System.out.println("相差的月数：" + between2.getMonths()); // 11
        System.out.println("相差的天数：" + between2.getDays()); // 17
        System.out.println("相差的总月数：" + between2.toTotalMonths()); // 59
    }

    /**
     * 持续时间 Duration，用于计算两个“时间”间隔
     */
    @Test
    public void test8() {
        LocalDateTime t1 = LocalDateTime.now();
        LocalDateTime t2 = LocalDateTime.of(2018, 8, 8, 20, 0, 0, 0);
        Duration between = Duration.between(t1, t2);
        System.out.println(between); //PT-43474H-58M-11.463042S
        System.out.println("相差的总天数：" + between.toDays()); //相差的总天数：-1811
        System.out.println("相差的总小时数：" + between.toHours()); //相差的总小时数：-43474
        System.out.println("相差的总分钟数：" + between.toMinutes()); //相差的总分钟数：-2608498
        System.out.println("相差的总秒数：" + between.getSeconds()); //相差的总秒数：-156509892
        System.out.println("相差的总毫秒数：" + between.toMillis()); //相差的总毫秒数：-156509891463
        System.out.println("相差的总纳秒数：" + between.toNanos()); //相差的总纳秒数：-156509891463042000
        System.out.println("不够一秒的纳秒数：" + between.getNano()); //不够一秒的纳秒数：536958000

        Duration between2 = Duration.between(t2, t1);
        System.out.println(between2); //PT43474H58M11.463042S
        System.out.println("相差的总天数：" + between2.toDays()); //1811
        System.out.println("相差的总小时数：" + between2.toHours()); //43474
        System.out.println("相差的总分钟数：" + between2.toMinutes()); //2608498
        System.out.println("相差的总秒数：" + between2.getSeconds()); //156509891
        System.out.println("相差的总毫秒数：" + between2.toMillis()); //156509891463
        System.out.println("相差的总纳秒数：" + between2.toNanos()); //156509891463042000
        System.out.println("不够一秒的纳秒数：" + between2.getNano()); //463042000
    }

    /**
     * 时间矫正器 TemporalAdjuster
     */
    @Test
    public void test9() {
        // 获取当前日期的下一个周日
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.SUNDAY);
        LocalDateTime localDateTime = LocalDateTime.now().with(temporalAdjuster);
        System.out.println(localDateTime); //2023-07-30T07:04:05.064134

        // 获取下一个工作日
        LocalDate localDate = LocalDate.now().with(new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                LocalDate date = (LocalDate) temporal;
                if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    return date.plusDays(3);
                } else if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    return date.plusDays(2);
                } else {
                    return date.plusDays(1);
                }
            }
        });
        System.out.println("下一个工作日是：" + localDate); //下一个工作日是：2023-07-26
    }
}
