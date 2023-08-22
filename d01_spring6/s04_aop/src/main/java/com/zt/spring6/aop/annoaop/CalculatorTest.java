package com.zt.spring6.aop.annoaop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zt
 * @since 2023/8/21 16:26
 **/
public class CalculatorTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Calculator calculator = applicationContext.getBean(Calculator.class);
        int num = calculator.div(1, 1);
        System.out.println("执行成功:" + num);
    }
}
