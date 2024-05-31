package structural.proxy.staticproxy;

import structural.proxy.SellTicket;
import structural.proxy.TrainStation;

/**
 * 结构型设计模式 - 代理模式 - 静态代理测试
 * 代售网点卖票
 *
 * @author zt
 * @date 2024/5/15
 **/
public class ProxyPoint implements SellTicket {
    private final TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        System.out.println("代售网点卖票，收取手续费");
        trainStation.sell();
    }
}
