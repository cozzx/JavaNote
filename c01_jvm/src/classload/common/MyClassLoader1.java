package classload.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author zt
 * @date 2023/12/31
 **/
public class MyClassLoader1 extends ClassLoader {
    public Class<?> loadClassFromFile(String fileName) throws ClassNotFoundException {
        try {
            byte[] classData = loadClassData(fileName);
            return defineClass(null, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class from file: " + fileName, e);
        }
    }

    private byte[] loadClassData(String fileName) throws IOException {
        Path path = Path.of(fileName);
        return Files.readAllBytes(path);
    }
}
