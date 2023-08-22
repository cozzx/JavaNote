package com.zt.spring6.resource;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 访问文件系统资源
 *
 * @author zt
 * @since 2023/8/22 07:20
 **/
public class FileSystemResourceTest {

    public void loadAndReadRulResource(String path) throws IOException {
        FileSystemResource resource = new FileSystemResource(path);
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
        InputStream inputStream = resource.getInputStream();
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) != -1) {
            System.out.println(new String(buffer));
        }
        System.out.println();
    }

    @Test
    public void test() throws IOException {
        loadAndReadRulResource("test3.txt");
    }
}
