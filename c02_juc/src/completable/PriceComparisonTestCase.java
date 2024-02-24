package completable;

import common.NetMall;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 电商网站比价案例
 *
 * @author zt
 * @date 2024/1/13
 **/
public class PriceComparisonTestCase {

    public final List<NetMall> netMallList = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dd"),
            new NetMall("tb")
    );

    public List<String> getPrice(List<NetMall> netMallList, String productName) {
        return netMallList.stream()
                .map(netMall -> String.format(
                                STR."《\{productName}》in \{netMall.getNetMallName()} price is %.2f",
                                netMall.calcPrice(productName)
                        )
                )
                .toList();
    }

    public List<String> getPriceCompletableFuture(List<NetMall> netMallList, String productName) {
        return netMallList.stream()
                .map(netMall -> CompletableFuture.supplyAsync(
                                () -> String.format(
                                        STR."《\{productName}》in \{netMall.getNetMallName()} price is %.2f",
                                        netMall.calcPrice(productName)
                                )
                        )
                )
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .toList();
    }

    @Test
    public void test() {
        long startTime = System.currentTimeMillis();
        System.out.println(getPrice(netMallList, "MySQL"));
        long endTime = System.currentTimeMillis();
        System.out.println(STR."Cost Time: \{endTime - startTime} 毫秒");

        startTime = System.currentTimeMillis();
        System.out.println(getPriceCompletableFuture(netMallList, "MySQL"));
        endTime = System.currentTimeMillis();
        System.out.println(STR."Cost Time: \{endTime - startTime} 毫秒");
    }
}
