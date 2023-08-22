package com.zt.spring6.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 关于反射的知识点
 *
 * @author zt
 * @since 2023/8/21 21:00
 **/
public class ReflectTest {

    /**
     * 获取 Class 对象的多种方式
     */
    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 方式一：类名.class
        Class<Car> carClass1 = Car.class;
        // 方式二：对象.getClass()
        Class<? extends Car> carClass2 = new Car().getClass();
        // 方式三：Class.forName("类的全路径")
        Class<?> carClass3 = Class.forName("com.zt.spring6.reflect.Car");

        // 实例化
        Car car = carClass1.getConstructor().newInstance();
        System.out.println("car = " + car);
    }

    /**
     * 获取构造方法
     */
    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Car> carClass = Car.class;

        // 获取所有构造方法
        // getConstructors() 是获取所有 public 的构造方法
        // getDeclaredConstructors() 获取所有的构造方法
        Constructor<?>[] declaredConstructors = carClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("method name: " + declaredConstructor.getName() + "; parameter count: " + declaredConstructor.getParameterCount());
        }

        // 调用私有构造方法，实例化对象
        Constructor<Car> declaredConstructor = carClass.getDeclaredConstructor(String.class, int.class, String.class);
        declaredConstructor.setAccessible(true);
        Car car = declaredConstructor.newInstance("长城", 9, "白色");
        System.out.println("car = " + car);
    }

    /**
     * 获取属性
     */
    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Car> carClass = Car.class;
        Car car = carClass.getDeclaredConstructor().newInstance();
        Field[] declaredFields = carClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("field name: " + declaredField.getName());
            declaredField.setAccessible(true);
            switch (declaredField.getName()) {
                case "name" -> declaredField.set(car, "坦克");
                case "age" -> declaredField.set(car, 0);
                case "color" -> declaredField.set(car, "黑色");
            }
        }
        System.out.println("car = " + car);
    }

    /**
     * 获取方法
     */
    @Test
    public void test4() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Car> carClass = Car.class;
        Car car = carClass.getDeclaredConstructor().newInstance();
        Method[] declaredMethods = carClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("method name: " + declaredMethod.getName() + "; parameter count: " + declaredMethod.getParameterCount());
            declaredMethod.setAccessible(true);
            switch (declaredMethod.getName()) {
                case "setName" -> declaredMethod.invoke(car, "tesla");
                case "run" -> declaredMethod.invoke(car);
            }
        }
        System.out.println("car = " + car);
    }
}
