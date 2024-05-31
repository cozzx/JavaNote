package com.cozz.common.api;

import com.cozz.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zt
 * @date 2024/3/10
 **/
@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    /**
     * 扣减库存
     */
    @PostMapping(value = "/storage/decrease")
    Result decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
