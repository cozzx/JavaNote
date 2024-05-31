package structural.proxy;

/**
 * 结构型设计模式 - 代理模式测试
 * 火车站卖票
 *
 * @author zt
 * @date 2024/5/15
 **/
public class TrainStation implements SellTicket {
    @Override
    public void sell() {
        System.out.println("火车站买票");
    }
}
