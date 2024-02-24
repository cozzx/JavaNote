package net;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author zt
 * @date 2023/6/30
 **/
public class InetAddressTest {

    @Test
    public void test1() throws IOException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());
        System.out.println(localHost.isReachable(3000));
        System.out.println();

        InetAddress byName1 = InetAddress.getByName("192.168.200.72");
        System.out.println(byName1);
        System.out.println(byName1.getHostAddress());
        System.out.println(byName1.getHostName());
        System.out.println(byName1.isReachable(3000));
        System.out.println();

        InetAddress byName2 = InetAddress.getByName("www.google.com");
        System.out.println(byName2);
        System.out.println(byName2.getHostAddress());
        System.out.println(byName2.getHostName());
        System.out.println(byName2.isReachable(3000));
        System.out.println();

        InetAddress byAddress = InetAddress.getByAddress(new byte[]{(byte) 198, 18, 0, (byte) 114});
        System.out.println(byAddress);
        System.out.println(byAddress.getHostAddress());
        System.out.println(byAddress.getHostName());
        System.out.println(byAddress.isReachable(3000));
    }
}
