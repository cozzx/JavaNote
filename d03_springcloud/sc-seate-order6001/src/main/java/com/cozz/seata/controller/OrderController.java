package com.cozz.seata.controller;

import com.cozz.common.util.Result;
import com.cozz.seata.entity.Order;
import com.cozz.seata.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2024/3/10
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/order/create")
    public Result create(@RequestBody Order order) {
        orderService.create(order);
        return Result.ok().data(order);
    }
}
