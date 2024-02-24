package jdbctx.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author zt
 * @date 2023/8/21
 **/
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CheckoutService checkoutService;

    public void buyBook(Integer bookId, Integer userId) {
        bookService.buyBook(bookId, userId);
    }

    public void checkout(Integer[] bookIds, Integer userId) {
        checkoutService.checkout(bookIds, userId);
    }
}
