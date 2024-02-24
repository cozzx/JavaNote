package common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author zt
 * @date 2024/1/13
 **/
@Data
@AllArgsConstructor
public class NetMall {

    private String netMallName;

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
