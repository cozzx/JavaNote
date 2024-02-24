package file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * File类测试
 *
 * @author zt
 * @date 2023/6/30
 **/
public class FileTest {

    /**
     * File 构造器测试
     */
    @Test
    public void test1() {

        // 1 通过路径，单元测试方法中的文件的相对路径，是相对于当前 module
        File file1 = new File("file.test");
        System.out.println(file1.getAbsolutePath());

        // 2 通过父路径和子路径
        File file2 = new File("src/common", "file.test");
        System.out.println(file2.getAbsolutePath());

        // 3 通过父对象和子路径
        File file3 = new File(new File("src/common"), "file.test");
        System.out.println(file3.getAbsolutePath());
    }

    /**
     * 获取目录或文件的信息
     */
    @Test
    public void test2() {

        // 目录
        File dir1 = new File("src/common");
        System.out.println("目录路径:" + dir1.getPath());
        System.out.println("目录绝对路径:" + dir1.getAbsolutePath());
        System.out.println("目录名称:" + dir1.getName());
        System.out.println("上层目录名称:" + dir1.getParent());
        System.out.println("目录长度:" + dir1.length() + "字节");
        System.out.println("目录最后修改时间：" + LocalDateTime.ofInstant(Instant.ofEpochMilli(dir1.lastModified()), ZoneId.of("Asia/Shanghai")));
        System.out.println();

        // 文件
        File file1 = new File("src/common/file.test");
        System.out.println("文件路径:" + file1.getPath());
        System.out.println("文件绝对路径:" + file1.getAbsolutePath());
        System.out.println("文件名称:" + file1.getName());
        System.out.println("上层文件名称:" + file1.getParent());
        System.out.println("文件长度:" + file1.length() + "字节");
        System.out.println("文件最后修改时间：" + LocalDateTime.ofInstant(Instant.ofEpochMilli(file1.lastModified()), ZoneId.of("Asia/Shanghai")));
        System.out.println();

        // 文件或目录不存在时，File 对象属性保留默认值
        File file2 = new File("src/common/file.test.no");
        System.out.println("文件路径:" + file2.getPath());
        System.out.println("文件绝对路径:" + file2.getAbsolutePath());
        System.out.println("文件名称:" + file2.getName());
        System.out.println("文件长度:" + file2.length() + "字节");
        System.out.println("文件最后修改时间：" + LocalDateTime.ofInstant(Instant.ofEpochMilli(file2.lastModified()), ZoneId.of("Asia/Shanghai")));
        System.out.println();

        // 判断
        System.out.println("是否实际存在:" + file1.exists());
        System.out.println("是否为目录:" + file1.isDirectory());
        System.out.println("是否为文件:" + file1.isFile());
        System.out.println("是否可读:" + file1.canRead());
        System.out.println("是否可写:" + file1.canWrite());
        System.out.println("是否隐藏:" + file1.isHidden());
    }

    /**
     * 目录中的子文件或目录
     */
    @Test
    public void test3() {

        File dir1 = new File("src/common/dir");
        String[] subs1 = dir1.list();
        for (String sub : subs1) {
            System.out.println(sub);
        }

        File dir2 = new File("src/common");
        File[] subs2 = dir2.listFiles();
        for (File sub : subs2) {
            System.out.println(sub.getAbsolutePath());
        }
    }

    /**
     * 创建、修改、删除功能
     */
    @Test
    public void test4() throws IOException {

        // 文件的创建
        File file1 = new File("src/common/aaa.test");
        System.out.println("aaa.test 是否存在:" + file1.exists());
        System.out.println("aaa.test 是否创建:" + file1.createNewFile());
        System.out.println("aaa.test 是否存在:" + file1.exists());

        // 目录的创建
        File dir1 = new File("src/common/dir");
        System.out.println("dir1 是否存在:" + dir1.exists());
        System.out.println("dir1 是否创建:" + dir1.mkdir());
        System.out.println("dir1 是否存在:" + dir1.exists());

        // 创建多级目录
        File dir2 = new File("src/common/dir2/dir2_1");
        System.out.println("src/common/dir2/dir2_1 创建：" + dir2.mkdirs());

        // 重命名
        File dir3 = new File("src/common/dir2/dir2_1");
        System.out.println("src/common/dir2/dir2_1 重命名：" + dir3.renameTo(new File("src/common/dir2/dir2-1")));

        // 文件的删除
        System.out.println("aaa.test 删除：" + file1.delete());

        // 目录的删除
        File dir4 = new File("src/common/dir2/dir2-1");
        System.out.println("src/common/dir2/ 删除：" + dir4.delete());
    }
}
