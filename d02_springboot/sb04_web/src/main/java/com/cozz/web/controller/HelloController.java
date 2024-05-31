package com.cozz.web.controller;

import com.cozz.web.bean.Person;
import com.cozz.web.service.FileUploadClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

/**
 * @author zt
 * @date 2023/8/29
 **/
@RestController
@Slf4j
public class HelloController {

    /**
     * 默认使用新版 PathPatternParser 进行路径匹配
     * 不能匹配 ** 在中间的情况，剩下的和 antPathMatcher 语法兼容
     * 如果需要中间带 ** 的路径匹配，需要修改配置文件 spring.mvc.pathmatch.matching-strategy=ant_path_matcher
     */
    @GetMapping("/a*/b?/{p1:[a-f]+}/**")
    public String hello(HttpServletRequest request, @PathVariable("p1") String path) {
        log.info("路径变量p1： {}", path);
        String uri = request.getRequestURI();
        return uri;
    }

    /**
     * 默认支持把对象写为json。因为默认web场景导入了jackson处理json的包 jackson-core
     * jackson 也支持把数据写为xml，需要导入xml相关依赖
     * 默认通过请求头 设置 Accept=application/json 或 Accept=application/xml 确定返回格式
     */
    @GetMapping("/person")
    public Person person() {
        Person person = new Person();
        person.setId(1L);
        person.setUserName("zt");
        person.setEmail("aaa@qq.com");
        person.setAge(18);
        return person;
    }

    /**
     * 可以开启基于请求参数的内容协商功能，默认参数 format，?format=json 或 ?format=xml
     * 配置文件设置 spring.mvc.contentnegotiation.favor-parameter=true
     */
    @GetMapping("/personFormat")
    public Person personXml() {
        Person person = new Person();
        person.setId(1L);
        person.setUserName("zt");
        person.setEmail("aaa@qq.com");
        person.setAge(18);
        return person;
    }


    /**
     * 国际化消息组件
     */
    @Autowired
    MessageSource messageSource;

    /**
     * 国际化
     */
    @GetMapping("/i18n")
    public String i18n(HttpServletRequest request) {
        Locale locale = request.getLocale();
        return messageSource.getMessage("login", null, locale);
    }

    private static final String UPLOAD_DIR = "/Users/zhangtao/Desktop/";

    @PostMapping("file/recv")
    public ResponseEntity<String> recvFile(@RequestParam("file") MultipartFile file) {
        try {
            // 创建文件对象
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // 获取文件名
            String fileName = file.getOriginalFilename();

            // 构建完整的文件路径
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // 将文件写入指定路径
            Files.write(path, file.getBytes());

            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private FileUploadClientService fileUploadClientService;


    @PostMapping("file/send")
    public void sendFile() {
        String filePath = "/Users/zhangtao/Downloads/exportOpcDA";
        String serverUrl = "http://localhost:8084/file/recv";
        fileUploadClientService.uploadFile(filePath, serverUrl);
    }

}
