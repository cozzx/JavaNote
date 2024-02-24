package jdbctx.tx;

/**
 * @author zt
 * @date 2023/8/21
 **/
public interface BookDAO {

    Integer getPriceByBookId(Integer bookId);

    void updateStock(Integer bookId);

    void updateBalance(Integer userId, Integer price);
}
