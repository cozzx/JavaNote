package com.cozz.seata.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cozz.common.api.AccountFeignApi;
import com.cozz.common.api.StorageFeignApi;
import com.cozz.seata.entity.Order;
import com.cozz.seata.mapper.OrderMapper;
import com.cozz.seata.serivce.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/3/10
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired//订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;

    @Autowired//订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "sc-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        //xid全局事务id的检查，重要
        String xid = RootContext.getXID();
        //1 新建订单
        log.info("---------------开始新建订单: " + "\t" + "xid: " + xid);
        //订单新建时默认初始订单状态是零
        order.setStatus(0);
        boolean isSave = this.save(order);
        // 插入订单成功后获得插入mysql的实体对象
        if (isSave) {
            // 从mysql里面查出刚插入的记录
            Order orderFromDB = this.getById(order.getId());
            log.info("-----> 新建订单成功,orderFromDB info: " + orderFromDB);
            System.out.println();
            //2 扣减库存
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();

            //3 扣减账户余额
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();

            //4 修改订单状态
            //将订单状态从零修改为1，表示已经完成
            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);
            boolean isUpdate = this.updateById(orderFromDB);
            log.info("-------> 修改订单状态完成" + "\t" + isUpdate);
            log.info("-------> orderFromDB info: " + orderFromDB);
        }
        System.out.println();
        log.info("---------------结束新建订单: " + "\t" + "xid: " + xid);
    }
}
