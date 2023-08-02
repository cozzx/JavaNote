package other;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * apache-common 包的使用
 *
 * @author zt
 * @since 2023/7/3 12:39
 **/
public class ApacheCommonTest {

    /**
     * IOUtils 类
     */
    @Test
    public void test1() throws IOException {
        IOUtils.copy(new FileInputStream("src/common/file.test"), new FileOutputStream("src/common/file.test.copy"));
        IOUtils.closeQuietly();
    }

    /**
     * FileUtils 类
     */
    @Test
    public void test2() throws IOException {
        FileUtils.copyDirectoryToDirectory(new File("src/common/dir"), new File("src/common/dir_bak"));
        FileUtils.copyFile(new File("src/common/file.test"), new File("src/common/file.test.copy"));
    }
}
