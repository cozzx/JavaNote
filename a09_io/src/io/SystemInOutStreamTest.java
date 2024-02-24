package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入输出流
 *
 * @author zt
 * @date 2023/7/3
 **/
public class SystemInOutStreamTest {

    @Test
    public void test1() {
        try (
                // 把"标准"输入流(键盘输入)这个字节流包装成字符流,再包装成缓冲流
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("请输入信息(退出输入e或exit):");
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)) {
                    System.out.println("安全退出!!");
                    break;
                }
                // 将读取到的整行字符串转成大写输出
                System.out.println("-->:" + s.toUpperCase());
                System.out.println("继续输入信息");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
