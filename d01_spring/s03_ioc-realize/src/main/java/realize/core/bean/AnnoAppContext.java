package realize.core.bean;

import realize.core.anno.MyBean;
import realize.core.anno.MyDI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zt
 * @date 2023/8/21
 **/
public class AnnoAppContext implements MyAppContext {

    String rootPath;

    // 存储 Bean 的容器
    private HashMap<Class, Object> beanFactory = new HashMap<>();

    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    /**
     * 根据包扫描加载 bean
     */
    public AnnoAppContext(String basePackage) {
        try {
            String packageDirName = basePackage.replaceAll("\\.", "/");
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                // ~/Code/Java/demo/JavaNote/d01_spring/s03_ioc-realize/target/classes/realize/test
                String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                // ~/Code/Java/demo/JavaNote/d01_spring/s03_ioc-realize/target/classes/
                rootPath = filePath.substring(0, filePath.length() - packageDirName.length());
                // Bean 注入
                loadBean(new File(rootPath));
                // 属性注入
                loadDI();
            }
        } catch (IOException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                 InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadBean(File fileParent) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (fileParent.isDirectory()) {
            File[] childrenFiles = fileParent.listFiles();
            if (childrenFiles == null || childrenFiles.length == 0) {
                return;
            }
            for (File childFile : childrenFiles) {
                if (childFile.isDirectory()) {
                    loadBean(childFile);
                } else {
                    // 通过文件路径转成全类名，realize/test/UserService.class
                    String pathWithClass = childFile.getAbsolutePath().substring(rootPath.length());
                    // 选中 class 文件
                    if (pathWithClass.contains(".class")) {
                        // 去掉 .class 后缀，把 「\」 替换成 「.」，test.realize.UserService
                        String fullName = pathWithClass.replaceAll("/", ".").replace(".class", "");
                        Class<?> aClass = Class.forName(fullName);
                        // 把非接口的类实例化，放到 map 中
                        if (!aClass.isInterface()) {
                            MyBean myBean = aClass.getAnnotation(MyBean.class);
                            if (myBean != null) {
                                Object instance = aClass.getDeclaredConstructor().newInstance();
                                // 判断一下有没有实现接口
                                if (aClass.getInterfaces().length > 0) {
                                    // 如果有实现接口把接口的 class 当成 key，实例对象当成 value
                                    System.out.println("正在加载【" + aClass.getInterfaces()[0] + "】,实例对象是：" + instance.getClass().getName());
                                    beanFactory.put(aClass.getInterfaces()[0], instance);
                                } else {
                                    // 如果没有接口把自己的 class 当成key，实例对象当成 value
                                    System.out.println("正在加载【" + aClass.getName() + "】,实例对象是：" + instance.getClass().getName());
                                    beanFactory.put(aClass, instance);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void loadDI() throws IllegalAccessException {
        for (Map.Entry<Class, Object> entry : beanFactory.entrySet()) {
            // 容器中的对象
            Object valueObj = entry.getValue();
            Class<?> aClass = valueObj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                MyDI myDI = field.getAnnotation(MyDI.class);
                if (myDI != null) {
                    field.setAccessible(true);
                    System.out.println("正在给【" + valueObj.getClass().getName() + "】属性【" + field.getName() + "】注入值【" + beanFactory.get(field.getType()).getClass().getName() + "】");
                    field.set(valueObj, beanFactory.get(field.getType()));
                }
            }
        }
    }
}
