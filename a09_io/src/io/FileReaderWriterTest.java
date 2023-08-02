package io;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流
 *
 * @author zt
 * @since 2023/6/30 12:09
 **/
public class FileReaderWriterTest {

    /**
     * FileReader
     */
    @Test
    public void test1() throws IOException {

        // 1 创建 File 类对象
        File file = new File("src/common/file.test");
        // 2 创建 FileReader 流对象，将 File 类的对象作为参数传递到 FileReader 构造器中
        FileReader fileReader = new FileReader(file);
        // 3 通过相关流的方法，读取文件中的数据
        int data;
        while ((data = fileReader.read()) != -1) {
            System.out.println((char) data);
        }
        // 4 关闭资源
        fileReader.close();
    }

    /**
     * 改进 test1
     * 增加 try catch 保证流关闭
     * 调用 read(char[] cbuf) 每次从文件中读取多个字符
     */
    @Test
    public void test2() {

        FileReader fileReader = null;
        try {
            File file = new File("src/common/file.test");
            fileReader = new FileReader(file);
            int len;
            char[] buffer = new char[5];
            while ((len = fileReader.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * FileWriter
     */
    @Test
    public void test3() {

        FileWriter fileWriter = null;
        try {
            // 使用文件名称创建流对象
            fileWriter = new FileWriter("src/common/file.test");

            // 写出单个字符
            fileWriter.write(97);
            fileWriter.write('b');
            fileWriter.write('C');
            fileWriter.write(30000);

            // 写出字符数组
            char[] chars = "Hello, IO 流。".toCharArray();
            fileWriter.write(chars);
            fileWriter.write(chars, 1, 2);

            // 写出字符串
            String msg = "Hello, IO 流。";
            fileWriter.write(msg);
            fileWriter.write(msg, 4, 5);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
