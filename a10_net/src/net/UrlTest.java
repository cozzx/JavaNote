package net;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zt
 * @since 2023/7/3 19:09
 **/
public class UrlTest {

    /**
     * URL 类对象及方法
     */
    @Test
    public void test1() {

        try {
            URL url = new URL("https://192.168.21.107:8080/examples/abcd?name=Tom&age=18#33");
            // 获取该URL的协议名 https
            System.out.println(url.getProtocol());
            // 获取该URL的主机名 192.168.21.107
            System.out.println(url.getHost());
            // 获取该URL的端口号 8080
            System.out.println(url.getPort());
            // 获取该URL的文件路径 /examples/abcd
            System.out.println(url.getPath());
            // 获取该URL的文件名 /examples/abcd?name=Tom&age=18
            System.out.println(url.getFile());
            // 获取该URL的查询名 name=Tom&age=18
            System.out.println(url.getQuery());
            // 获取该URL的锚点 33
            System.out.println(url.getRef());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 需求：将URL代表的资源下载到本地
     * */
    @Test
    public void test2() {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 1. 获取URL实例
            URL url = new URL("http://127.0.0.1:80/test/src.png");
            // 2. 建立与服务器端的连接
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 3. 获取输入流、创建输出流
            inputStream = httpURLConnection.getInputStream();
            fileOutputStream = new FileOutputStream("src/common/dst.png");
            // 4. 读写数据
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            System.out.println("文件下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭资源
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }


    }
}
