package net;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * UDP通信测试
 *
 * @author zt
 * @since 2023/7/3 18:47
 **/
public class UdpTest {

    /**
     * UDP 客户端
     */
    @Test
    public void test1() {

        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte[] msg = "hello, I'm UDP packet".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(msg, 0, msg.length, new InetSocketAddress("127.0.0.1", 10000));
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * UDP 服务端
     */
    @Test
    public void test2() {

        try (DatagramSocket datagramSocket = new DatagramSocket(10000)) {
            byte[] bytes = new byte[1024 * 64];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
            datagramSocket.receive(datagramPacket);
            String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println(str + "--" + datagramPacket.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * UDP 服务端 循环处理
     */
    @Test
    public void test3() {

        try (DatagramSocket datagramSocket = new DatagramSocket(10000)) {
            while (true) {
                byte[] bytes = new byte[1024 * 64];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
                datagramSocket.receive(datagramPacket);
                String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println(str + "--" + datagramPacket.getAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
