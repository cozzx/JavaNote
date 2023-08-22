package com.zt.spring6.tx;

/**
 * @author zt
 * @since 2023/8/21 22:59
 **/
public interface BookDAO {

    Integer getPriceByBookId(Integer bookId);

    void updateStock(Integer bookId);

    void updateBalance(Integer userId, Integer price);
}
