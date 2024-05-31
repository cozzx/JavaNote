package com.cozz.gateway.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言, 自定义配置会员等级 usertype，按照钻/金/银和yml配置的会员等级，以适配是否可以访问
 *
 * @author zt
 * @date 2024/3/3
 **/
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    // 这个 Config 类就是我们的路由断言规则
    @Setter
    @Getter
    @Validated
    public static class Config {

        // 钻/金/银和yml配置的会员等级
        @NotEmpty
        private String userType;
    }

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 检查request的参数里面，userType是否为指定的值，符合配置就通过
                // http://localhost:9527/payment/gateway/get/1?userType=gold
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                return userType != null && userType.equalsIgnoreCase(config.getUserType());
            }
        };
    }
}
