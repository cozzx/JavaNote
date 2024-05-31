package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO 文件操作
 *
 * @author zt
 * @date 2024/5/9
 **/
public class FileChannelTest {

    /**
     * 文件拷贝
     */
    @Test
    public void testTransferTo() {
        try (
                FileChannel from = new FileInputStream("assets/from.log").getChannel();
                FileChannel to = new FileOutputStream("assets/to.log").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化，大小限制为2G
            long size = from.size();
            // left 变量代表还剩余多少字节
            for (long left = size; left > 0; ) {
                System.out.println("position:" + (size - left) + " left:" + left);
                left -= from.transferTo(0, size, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历目录
     */
    @Test
    public void testFilesWalkFileTree() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("/usr/local/Cellar/openjdk/21.0.1"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("====>" + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });

        System.out.println("dir count:" + dirCount);
        System.out.println("file count:" + fileCount);
    }

    /**
     * 遍历特定文件
     */
    @Test
    public void testFilesWalkFile() throws IOException {
        AtomicInteger jarCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("/usr/local/Cellar/openjdk/21.0.1"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".jar")) {
                    System.out.println(file);
                    jarCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });

        System.out.println("jar count:" + jarCount);
    }

    /**
     * 删除文件夹
     */
    @Test
    public void testFilesWalkFileDelete() throws IOException {
        Files.walkFileTree(Paths.get("/usr/local/Cellar/openjdk/21.0.1_bak"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    /**
     * 目录文件拷贝
     */
    @Test
    public void testFilesCopy() throws IOException {
        String source = "/usr/local/Cellar/openjdk/21.0.1";
        String target = "/usr/local/Cellar/openjdk/21.0.1_bak";
        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                if (Files.isDirectory(path)) {
                    Files.createDirectory(Paths.get(targetName));
                } else if (Files.isRegularFile(path)) {
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
