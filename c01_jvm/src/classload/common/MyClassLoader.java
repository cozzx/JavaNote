package classload.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zt
 * @date 2023/12/30
 **/
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String replaced = className.replace(".", File.separator);
            byte[] data = loadClassData(replaced + ".class");
            return defineClass(className, data, 0, data.length);
        } catch (IOException | URISyntaxException e) {
            throw new ClassNotFoundException(className);
        }
    }

    private byte[] loadClassData(String className) throws IOException, URISyntaxException {
        // 假设类文件位于类加载器的同级目录下的一个名为"classes"的子目录中。
        URL resource = getResource(className);
        if (resource == null) {
            throw new FileNotFoundException("Could not find: " + className);
        }
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }
}
