package com.zt.spring6.tx;

/**
 * @author zt
 * @since 2023/8/21 23:33
 **/
public interface CheckoutService {

    void checkout(Integer[] bookIds, Integer userId);
}
