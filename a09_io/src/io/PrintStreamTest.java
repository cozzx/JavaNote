package io;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 打印流
 *
 * @author zt
 * @since 2023/7/3 08:24
 **/
public class PrintStreamTest {

    /**
     * 打印流测试
     */
    @Test
    public void test1() {
        try (
                PrintStream printStream = new PrintStream("src/common/file.test");
        ) {
            printStream.println("hello");
            printStream.println(1);
            printStream.println(1.5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印流小功能：把ASCII码写到文件中
     */
    @Test
    public void test2() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("src/common/file.test");
                // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
                PrintStream printStream = new PrintStream(fileOutputStream, true);
        ) {
            // 把标准输出流(控制台输出)改成文件
            System.setOut(printStream);
            for (int i = 0; i <= 255; i++) {
                // 输出 ASCII 字符
                System.out.print((char) i);
                // 每50个数据一行
                if (i % 50 == 0) {
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印流小功能：记录日志
     */
    public void setLog(String msg) {
        try {
            // 指向一个日志文件
            PrintStream out = new PrintStream(new FileOutputStream("src/common/tmp.log", true));
            // 改变输出方向
            System.setOut(out);
            // 日期当前时间
            Date nowTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            String strTime = sdf.format(nowTime);

            System.out.println(strTime + ": " + msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        //测试工具类是否好用
        setLog("用户尝试进行登录，验证失败");
    }

    /**
     * Scanner 类
     */
    @Test
    public void test4() {
        try (
                Scanner input = new Scanner(System.in);
                PrintStream printStream = new PrintStream("src/common/file.test");
        ) {

            while (true) {
                System.out.print("请输入一个单词：");
                String str = input.nextLine();
                if ("stop".equals(str)) {
                    break;
                }
                printStream.println(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scanner 类
     */
    @Test
    public void test5() {
        try (
                Scanner input = new Scanner(new FileInputStream("src/common/tmp.log"));
        ) {

            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
