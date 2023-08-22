package com.zt.spring6.resource;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.UrlResource;

import java.io.IOException;

/**
 * 访问网络资源
 *
 * @author zt
 * @since 2023/8/22 07:07
 **/
public class UrlResourceTest {

    public void loadAndReadUrlResource(String path) {
        // 创建一个 Resource 对象
        UrlResource urlResource = null;
        try {
            urlResource = new UrlResource(path);
            // 获取资源名
            System.out.println(urlResource.getFilename());
            System.out.println(urlResource.getURI());
            // 获取资源描述
            System.out.println(urlResource.getDescription());
            // 获取资源内容
            System.out.println(urlResource.getInputStream().read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        // 访问网络资源
        loadAndReadUrlResource("https://www.baidu.com");
    }

    @Test
    public void test2() {
        // 访问模块根目录下的资源
        loadAndReadUrlResource("file:test1.txt");
    }
}
