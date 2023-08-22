package com.zt.spring6.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zt
 * @since 2023/8/21 22:59
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    /**
     * service 层一个方法表示一个完成的功能，因此处理事务一般在 service 层处理
     */
    @Override
//    @Transactional
//    @Transactional(readOnly = true) // 对一个查询操作可以设置成只读，这样数据库就能够针对查询操作来进行优化，对增删改操作设置只读会抛异常
//    @Transactional(timeout = 3) // 超时回滚
//    @Transactional(noRollbackFor = ArithmeticException.class) // 设置回滚策略
//    @Transactional(isolation = Isolation.DEFAULT) // 使用数据库默认的隔离级别
//    @Transactional(isolation = Isolation.READ_UNCOMMITTED) // 读未提交
//    @Transactional(isolation = Isolation.READ_COMMITTED) // 读已提交
//    @Transactional(isolation = Isolation.REPEATABLE_READ) // 可重复读
//    @Transactional(isolation = Isolation.SERIALIZABLE) // 串行化
    // 默认，如果当前线程上有已经开启的事务可用，那么就在这个事务中运行。在购买第二本图书时余额不足失败，导致整个checkout()回滚，即只要有一本书买不了，就都买不了。
//    @Transactional(propagation = Propagation.REQUIRED)
    // 表示不管当前线程上是否有已经开启的事务，都要开启新事务。第一本图书购买成功，事务结束，第二本图书购买失败，只在第二次的buyBook()中回滚，购买第一本图书不受影响，即能买几本就买几本。
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyBook(Integer bookId, Integer userId) {

        // 查询图书的价格
        Integer price = bookDAO.getPriceByBookId(bookId);

        // 更新图书的库存
        bookDAO.updateStock(bookId);
        System.out.println("扣库存");

        // 更新用户的余额
        bookDAO.updateBalance(userId, price);
        System.out.println("扣金额");
    }
}
