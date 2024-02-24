package net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多个客户端与服务器之间的多次通信
 *
 * @author zt
 * @date 2023/7/3
 **/
public class TcpTest {

    /**
     * 服务端
     */
    @Test
    public void test1() {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("等待连接...");

            while (true) {
                Socket accept = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        InputStream inputStream = null;
                        OutputStream outputStream = null;
                        try {
                            inputStream = accept.getInputStream();
                            StringBuilder stringBuilder = new StringBuilder();
                            byte[] bytes = new byte[1024];
                            int len;
                            while ((len = inputStream.read(bytes)) != -1) {
                                stringBuilder.append(new String(bytes, 0, len));
                            }
                            System.out.println("收到来自 " + accept.getInetAddress().getHostAddress() + ":" + accept.getPort() + "的连接。接收内容为：" + stringBuilder);

                            outputStream = accept.getOutputStream();
                            outputStream.write("登录成功".getBytes());
                            outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (accept != null) {
                                try {
                                    accept.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 客户端
     */
    @Test
    public void test2() {

        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            socket = new Socket("127.0.0.1", 8888);
            outputStream = socket.getOutputStream();
            outputStream.write("我是客户端".getBytes());
            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            byte[] data = new byte[1024];
            StringBuilder s = new StringBuilder();
            int len;
            while ((len = inputStream.read(data)) != -1) {
                s.append(new String(data, 0, len));
            }
            System.out.println("服务器返回的消息是：" + s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
