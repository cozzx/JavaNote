package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author zt
 * @date 2024/5/8
 **/
public class ByteBufferTest {

    @Test
    public void testReadWrite1() {
        // 1. 获取文件流通道
        try (
                FileInputStream fileInputStream = new FileInputStream("assets/data.log");
                FileChannel channel = fileInputStream.getChannel()
        ) {
            // 2. 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            // 3. 从channel去读数据，读到buffer中
            channel.read(buffer);
            // 4. 打印buffer的内容
            // 4.1. 切换到读模式
            buffer.flip();
            // 4.2. 判断是否还有剩余数据，每次获取一个字符
            do {
                // 向 buffer 写入
                int len = channel.read(buffer);
                System.out.println("读到字节数：" + len);
                if (len == -1) {
                    break;
                }
                // 切换 buffer 读模式
                buffer.flip();
                while(buffer.hasRemaining()) {
                    System.out.println((char)buffer.get());
                }
                // 切换 buffer 写模式
                buffer.clear();
            } while (true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAllocate() {
        // java 堆内存，读写效率低，收到GC影响
        System.out.println(ByteBuffer.allocate(16).getClass());
        // 直接内存，读写效率高（少一次拷贝），不受GC影响，内存分配效率低
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
    }

    @Test
    public void testReadWrite2() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 写入
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        ByteBufferDebug.debugAll(buffer);
        // 切换读模式
        buffer.flip();
        ByteBufferDebug.debugAll(buffer);
        // 读到最后
        buffer.get(new byte[4]);
        ByteBufferDebug.debugAll(buffer);
        // 从头开始读
        buffer.rewind();
        ByteBufferDebug.debugAll(buffer);
        // mark 做一个标记，记录 position 位置， reset 是将 position 重置到 mark 的位置
        buffer.get(new byte[2]);
        buffer.mark();
        buffer.get(new byte[2]);
        buffer.reset();
        ByteBufferDebug.debugAll(buffer);
        // get(i) 不会改变读索引的位置
        System.out.println(buffer.get(3));
        ByteBufferDebug.debugAll(buffer);
    }

    /**
     * 测试字符串转换
     */
    @Test
    public void testStringConv() {
        // 将字符串写入buffer
        // 1. 字符串转换为字节数组，再使用 put 方法，此结果读取需要手动转为读模式
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());
        ByteBufferDebug.debugAll(buffer1);
        // 2. 使用 Charset 将字符串转为 bytebuffer，结果自动为读模式
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        ByteBufferDebug.debugAll(buffer2);
        // 3. 使用 bytebuffer 的 warp 将字节数组转为 bytebuffer，结果自动为读模式
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        ByteBufferDebug.debugAll(buffer3);
    }

    /**
     * 将多个buffer一次性写入通道
     */
    @Test
    public void testGatheringWrites() {
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好");

        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile("assets/words2.log", "rw");
                FileChannel channel = randomAccessFile.getChannel()
        ) {
            channel.write(new ByteBuffer[]{b1, b2, b3});
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 将文件字节分别读取到多个buffer
     */
    @Test
    public void testScatteringReads() {
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile("assets/words.txt", "r");
                FileChannel channel = randomAccessFile.getChannel();
        ) {
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();
            ByteBufferDebug.debugAll(b1);
            ByteBufferDebug.debugAll(b2);
            ByteBufferDebug.debugAll(b3);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 测试黏包和半包
     */
    @Test
    public void testSplit() {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(buffer);
        buffer.put("w are you?\n".getBytes());
        split(buffer);
    }
    public void split(ByteBuffer buffer) {
        // 切换为读模式
        buffer.flip();
        // 循环读内容
        for (int i = 0; i < buffer.limit(); i++) {
            // 找到一条完整的消息
            if (buffer.get(i) == '\n') {
                int length = i - buffer.position() + 1;
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(buffer.get());
                }
                ByteBufferDebug.debugAll(target);
            }
        }
        buffer.compact();
    }
}
