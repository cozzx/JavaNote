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
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class BuyTest {

    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook() {
        bookController.buyBook(1, 1);
    }
}
