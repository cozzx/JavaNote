package com.cozz.feat;

import com.cozz.feat.service.SumService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

/**
 * 测试类
 * 测试类也必须在主程序所在的包及其子包
 *
 * @author zt
 * @date 2023/8/30
 **/
@SpringBootTest  //具备测试SpringBoot应用容器中所有组件的功能
@Slf4j
public class Sb6MainApplicationTest {

    // 自动注入任意组件即可测试
    @Autowired
    SumService sumService;

    @Test
    public void test1() {
        int sum = sumService.sum(1, 2);
        log.info("测试类 test1 => " + sum);
    }

    @Test
    @DisplayName("测试类2") // 设置展示名称
    public void test2() {
        log.info("测试类 test2");
    }

    @BeforeAll  // 所有测试方法运行之前先运行这个：只打印一次
    public static void beforeAll() {
        log.info("测试类 beforeAll");
    }

    @BeforeEach // 每个测试方法运行之前先运行这个：每个方法运行打印一次
    public void beforeEach() {
        log.info("测试类 beforeEach");
    }

    @ParameterizedTest // 参数化测试
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("参数化测试1")
    public void parameterizedTest1(String string) {
        System.out.println(string);
        // 断言
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }

    @ParameterizedTest
    @MethodSource("method") // 指定方法名,返回值就是测试用的参数
    @DisplayName("参数化测试-方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    // 返回Stream即可
    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }
}
