package com.zt.spring6.i18n;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.Locale;

/**
 * Spring6 中的国际化实现
 *
 * @author zt
 * @since 2023/8/22 20:10
 **/
public class SpringI18nTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        // 传递动态参数，使用数组形式对应{0} {1}顺序
        Object[] objs = new Object[]{"fish", new Date().toString()};

        // objs 为资源文件 value 值所需要的参数，Local.CHINA 为国际化为语言
        String str = applicationContext.getMessage("welcome", objs, Locale.CHINA);
        System.out.println(str);
    }
}
