package com.zt.spring6.xmlconf;

import com.zt.spring6.tx.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于XML的声明式事务
 *
 * @author zt
 * @since 2023/8/22 00:11
 **/
public class TxByXmlTest {

    @Test
    public void testTxAllAnnotation() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-xmltx.xml");
        BookController bookController = applicationContext.getBean("bookController", BookController.class);
        bookController.buyBook(1, 1);
    }
}
