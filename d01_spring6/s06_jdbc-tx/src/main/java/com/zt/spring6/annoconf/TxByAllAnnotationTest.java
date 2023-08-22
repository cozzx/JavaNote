package com.zt.spring6.annoconf;

import com.zt.spring6.tx.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 全注解配置事务测试
 *
 * @author zt
 * @since 2023/8/22 00:01
 **/
public class TxByAllAnnotationTest {

    @Test
    public void testTxAllAnnotation() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookController accountService = applicationContext.getBean("bookController", BookController.class);
        accountService.buyBook(1, 1);
    }
}
