package com.zt.spring6.resource;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 访问类路径下资源
 *
 * @author zt
 * @since 2023/8/22 07:20
 **/
public class ClassPathResourceTest {

    public void loadAndReadRulResource(String path) throws IOException {
        // 创建一个 Resource 对象
        ClassPathResource resource = new ClassPathResource(path);
        // 获取文件名
        System.out.println(resource.getFilename());
        // 获取文件描述
        System.out.println(resource.getDescription());
        // 获取文件内容
        InputStream inputStream = resource.getInputStream();
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) != -1) {
            System.out.println(new String(buffer));
        }
        System.out.println();
    }

    @Test
    public void test() throws IOException {
        loadAndReadRulResource("test2.txt");
    }
}
