package jdbctx.tx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author zt
 * @date 2023/8/21
 **/
@Component
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
