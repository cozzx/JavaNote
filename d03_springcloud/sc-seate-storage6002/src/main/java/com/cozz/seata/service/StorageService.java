package com.cozz.seata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cozz.seata.entity.Storage;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/3/10
 **/
@Service
public interface StorageService extends IService<Storage> {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
