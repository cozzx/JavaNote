package com.cozz.seata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cozz.seata.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zt
 * @date 2024/3/10
 **/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}