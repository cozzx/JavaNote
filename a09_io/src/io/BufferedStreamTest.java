package io;

import org.junit.Test;

import java.io.*;

/**
 * 缓冲流
 *
 * @author zt
 * @date 2023/6/30
 **/
public class BufferedStreamTest {

    /**
     * 缓冲流的构造器
     */
    @Test
    public void test1() {

        try (
                // 创建字符缓冲输入流
                FileReader fileReader = new FileReader("src/common/file.test");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // 创建字符缓冲输出流
                FileWriter fileWriter = new FileWriter("src/common/file.test.1");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // 创建字节缓冲输入流
                FileInputStream fileInputStream = new FileInputStream("src/common/file.test");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                // 创建字节缓冲输出流
                FileOutputStream fileOutputStream = new FileOutputStream("src/common/file.test.1");
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件复制，缓冲流效率测试
     */
    @Test
    public void test2() {

        try (
                // 创建字节缓冲输入流
                FileInputStream fileInputStream = new FileInputStream("src/common/200M.file");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                // 创建字节缓冲输出流
                FileOutputStream fileOutputStream = new FileOutputStream("src/common/200M.file.copy1");
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ) {
//            long start1 = System.currentTimeMillis();
//
//            byte[] buffer1 = new byte[100];
//            int len1;
//            while ((len1 = fileInputStream.read(buffer1)) != -1) {
//                fileOutputStream.write(buffer1, 0, len1);
//            }
//            System.out.println("复制成功，耗时：" + (System.currentTimeMillis() - start1));

            long start2 = System.currentTimeMillis();
            byte[] buffer2 = new byte[100];
            int len2;
            while ((len2 = bufferedInputStream.read(buffer2)) != -1) {
                bufferedOutputStream.write(buffer2, 0, len2);
            }
            System.out.println("复制成功，耗时：" + (System.currentTimeMillis() - start2));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符缓冲流特有方法：readLine, newLine
     */
    @Test
    public void test3() {

        try (
                // 创建字符缓冲输入流
                FileReader fileReader = new FileReader("src/common/file.test");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // 创建字符缓冲输出流
                FileWriter fileWriter = new FileWriter("src/common/file.test");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            // 每次读取一行
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // 写入换行符
            bufferedWriter.write("网");
            bufferedWriter.write("不");
            bufferedWriter.newLine();
            bufferedWriter.write("好");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
