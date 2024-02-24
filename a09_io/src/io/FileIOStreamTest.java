package io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节流
 *
 * @author zt
 * @date 2023/6/30
 **/
public class FileIOStreamTest {

    /**
     * FileInputStream
     */
    @Test
    public void test1() throws IOException {

        // 1 创建 File 类对象
        File file = new File("src/common/file.test");
        // 2 创建 FileReader 流对象，将 File 类的对象作为参数传递到 FileReader 构造器中
        FileInputStream fileInputStream = new FileInputStream(file);
        // 3 通过相关流的方法，读取文件中的数据
        int data;
        while ((data = fileInputStream.read()) != -1) {
            // 输出单个字节，出现中文会乱码
            System.out.println((char) data);
        }
        // 4 关闭资源
        fileInputStream.close();
    }

    /**
     * 改进 test1
     * 增加 try catch 保证流关闭
     * 调用 read(byte b[]) 每次从文件中读取多个字符
     */
    @Test
    public void test2() {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/common/file.test");
            int len;
            byte[] buffer = new byte[5];
            while ((len = fileInputStream.read(buffer)) != -1) {
                // 输出字节数组，出现中文截断会乱码
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * FileOutputStream
     */
    @Test
    public void test3() {

        FileOutputStream fileOutputStream = null;
        try {
            // 使用文件名称创建流对象
            fileOutputStream = new FileOutputStream("src/common/file.test");

            // 写出单个字符
            fileOutputStream.write(97);
            fileOutputStream.write('b');
            fileOutputStream.write('C');
            fileOutputStream.write(300);

            // 刷新输出流，写出字节
            fileOutputStream.flush();

            // 写出字符数组
            byte[] bytes = "Hello, IO 流。".getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.write(bytes, 1, 2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
