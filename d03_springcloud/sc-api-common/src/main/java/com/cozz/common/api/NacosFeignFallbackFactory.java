package com.cozz.common.api;

import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @date 2024/3/9
 **/
@Component
public class NacosFeignFallbackFactory implements FallbackFactory<NacosFeignApi> {

    @Override
    public NacosFeignApi create(Throwable cause) {
        return new NacosFeignApi() {
            @Override
            public Result create(Payment payment) {
                return Result.err("服务出现异常,请稍后再试");
            }

            @Override
            public Result getPaymentById(Long id) {
                return Result.err("服务出现异常,请稍后再试");
            }

            @Override
            public Result testSentinelById(Long id) {
                return Result.err("服务出现异常,请稍后再试");
            }
        };
    }
}
