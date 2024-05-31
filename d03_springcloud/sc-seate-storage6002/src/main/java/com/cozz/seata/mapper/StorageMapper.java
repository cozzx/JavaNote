package com.cozz.seata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cozz.seata.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zt
 * @date 2024/3/10
 **/
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}