package com.zt.spring6.tx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author zt
 * @since 2023/8/21 23:05
 **/
@SpringJUnitConfig(locations = "classpath:beans-tx.xml")
public class BuyTxTest {

    @Autowired
    private BookController bookController;

    /**
     * 测试事务
     */
    @Test
    public void testBuyBook() {
        bookController.buyBook(1, 1);
    }

    /**
     * 测试事务传播
     */
    @Test
    public void testCheckout() {
        Integer[] bookIds = {1, 2};
        bookController.checkout(bookIds, 1);
    }
}
