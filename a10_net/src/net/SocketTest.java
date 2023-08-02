package net;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zt
 * @since 2023/6/30 12:28
 **/
public class SocketTest {

    /**
     * 客户端
     */
    @Test
    public void test1() {

        Socket socket = null;
        OutputStream outputStream = null;

        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            // 创建 socket
            socket = new Socket(inetAddress, 12345);
            outputStream = socket.getOutputStream();
            outputStream.write("你好，我是客户端。".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 服务端，接收消息输出到控制台
     */
    @Test
    public void test2() {

        try (
                // 创建 ServerSocket
                ServerSocket serverSocket = new ServerSocket(12345);
                // 调用 accept()，接收客户端的 Socket
                Socket acceptSocket = serverSocket.accept();
                // 通过Socket获取一个输入流
                InputStream inputStream = acceptSocket.getInputStream();
                // 缓冲输出流
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {

            System.out.println("收到来自 " + acceptSocket.getInetAddress().getHostAddress() + ":" + acceptSocket.getPort() + "的连接。");
            byte[] buffer = new byte[5];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            System.out.println(byteArrayOutputStream.toString());
            System.out.println("数据接收完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务端，接收消息输出到文件
     */
    @Test
    public void test3() {

        try (
                // 创建 ServerSocket
                ServerSocket serverSocket = new ServerSocket(12345);
                // 调用 accept()，接收客户端的 Socket
                Socket acceptSocket = serverSocket.accept();
                // 通过Socket获取一个输入流
                InputStream inputStream = acceptSocket.getInputStream();
                // 文件输出流
                FileOutputStream fileOutputStream = new FileOutputStream("src/common/file.test");
        ) {

            System.out.println("收到来自 " + acceptSocket.getInetAddress().getHostAddress() + ":" + acceptSocket.getPort() + "的连接。");
            byte[] buffer = new byte[5];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            System.out.println("数据接收完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端，发送消息并等待服务端返回
     */
    @Test
    public void test4() {

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

    /**
     * 服务端，接收消息并返回客户端消息
     */
    @Test
    public void test5() {

        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            // 1、准备一个ServerSocket对象，并绑定8888端口
            serverSocket = new ServerSocket(8888);

            // 2、在8888端口监听客户端的连接，该方法是个阻塞的方法，如果没有客户端连接，将一直等待
            accept = serverSocket.accept();

            // 3、获取输入流，接收该客户端发送给服务器的数据
            inputStream = accept.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                stringBuilder.append(new String(bytes, 0, len));
            }
            System.out.println("收到来自 " + accept.getInetAddress().getHostAddress() + ":" + accept.getPort() + "的连接。接收内容为：" + stringBuilder);

            // 4、获取输出流，发送数据给该客户端
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
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
