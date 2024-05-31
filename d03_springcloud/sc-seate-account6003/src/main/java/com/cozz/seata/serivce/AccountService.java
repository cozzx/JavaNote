package com.cozz.seata.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cozz.seata.entity.Account;
import com.cozz.seata.mapper.AccountMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/3/10
 **/
@Service
public interface AccountService extends IService<Account> {

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
