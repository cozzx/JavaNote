package io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 转换流
 *
 * @author zt
 * @date 2023/6/30
 **/
public class ConvStreamTest {

    @Test
    public void test() {

        try (
                FileInputStream fileInputStream = new FileInputStream("src/common/file.test");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

                FileOutputStream fileOutputStream = new FileOutputStream("src/common/file.test.1");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gbk");
        ) {
            char[] chars = new char[10];
            int len;
            while ((len = inputStreamReader.read(chars)) != -1) {
                outputStreamWriter.write(chars, 0, len);
            }
            System.out.println("转换完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
