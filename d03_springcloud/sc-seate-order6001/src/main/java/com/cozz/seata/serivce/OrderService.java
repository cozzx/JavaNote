package com.cozz.seata.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cozz.seata.entity.Order;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/3/10
 */
@Service
public interface OrderService extends IService<Order> {

    void create(Order order);
}
