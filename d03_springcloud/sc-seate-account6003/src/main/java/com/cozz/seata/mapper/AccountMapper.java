package com.cozz.seata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cozz.seata.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zt
 * @date 2024/3/10
 **/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * @param userId
     * @param money  本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}

