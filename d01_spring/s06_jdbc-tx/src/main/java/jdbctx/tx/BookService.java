package jdbctx.tx;

/**
 * @author zt
 * @date 2023/8/21
 **/
public interface BookService {

    void buyBook(Integer bookId, Integer userId);
}
