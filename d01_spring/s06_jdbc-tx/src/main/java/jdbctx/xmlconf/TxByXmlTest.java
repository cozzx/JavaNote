package jdbctx.xmlconf;

import jdbctx.tx.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于XML的声明式事务
 *
 * @author zt
 * @date 2023/8/22
 **/
public class TxByXmlTest {

    @Test
    public void testTxAllAnnotation() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-xmltx.xml");
        BookController bookController = applicationContext.getBean("bookController", BookController.class);
        bookController.buyBook(1, 1);
    }
}
