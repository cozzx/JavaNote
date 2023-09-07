package com.zt.web.controller;

import com.zt.web.bean.Person;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author zt
 * @since 2023/8/29 07:22
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
    public String I18n(HttpServletRequest request) {
        Locale locale = request.getLocale();
        return messageSource.getMessage("login", null, locale);
    }
}
