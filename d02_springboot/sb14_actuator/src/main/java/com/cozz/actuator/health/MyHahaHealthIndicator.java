package com.cozz.actuator.health;

import com.cozz.actuator.component.MyHahaComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @date 2023/9/6
 **/
@Component
public class MyHahaHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    MyHahaComponent myHahaComponent;

    /**
     * 健康检查
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int check = myHahaComponent.check();
        if (check == 1) {
            //存活
            builder.up()
                    .withDetail("code", "1000")
                    .withDetail("msg", "活的很健康")
                    .withDetail("data", "我的名字叫haha")
                    .build();
        } else {
            //下线
            builder.down()
                    .withDetail("code", "1001")
                    .withDetail("msg", "死的很健康")
                    .withDetail("data", "我的名字叫haha完蛋")
                    .build();
        }
    }
}
