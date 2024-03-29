package com.cozz.actuator.component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

/**
 * @author zt
 * @date 2023/9/6
 **/
@Component
public class MyHahaComponent {

    Counter counter = null;

    /**
     * 注入 meterRegistry 来保存和统计所有指标
     */
    public MyHahaComponent(MeterRegistry meterRegistry) {
        // 得到一个名叫 myHaha.hello 的计数器
        counter = meterRegistry.counter("myHaha.hello");
    }

    public int check() {
        // 业务代码判断这个组件是否该是存活状态
        return 1;
    }

    public void hello() {
        System.out.println("hello");
        counter.increment();
    }
}
