package com.cozz.common.api;

import com.cozz.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zt
 * @date 2024/3/10
 **/
@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    // 扣减账户余额
    @PostMapping("/account/decrease")
    Result decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
