package io;

import common.Employee;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据流，对象流
 *
 * @author zt
 * @since 2023/7/3 07:24
 **/
public class DataObjStreamTest {

    /**
     * 数据流
     */
    @Test
    public void test1() {
        try (
                DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("src/common/file.test.2"));
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream("src/common/file.test.2"));
        ) {
            dataOutputStream.writeByte(66);
            dataOutputStream.writeShort(500);
            dataOutputStream.writeInt(3000);
            dataOutputStream.writeLong(111111111L);
            dataOutputStream.writeFloat(54.5F);
            dataOutputStream.writeDouble(54.5);
            dataOutputStream.writeBoolean(false);
            dataOutputStream.writeChar('男');
            dataOutputStream.writeUTF("data数据");


            System.out.println(dataInputStream.read());
            System.out.println(dataInputStream.readShort());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readLong());
            System.out.println(dataInputStream.readFloat());
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readBoolean());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象流
     */
    @Test
    public void test2() {
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/common/file.test.2"));
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/common/file.test.2"));
        ) {

            // 除了数据流的写入类型，还多了对象类型的写入与读取
            // static 修饰的类变量，不会被序列化
            // transient 瞬态修饰成员,不会被序列化
            objectOutputStream.writeObject(new Employee(1, "as", 22, 3333));

            Employee e = (Employee) objectInputStream.readObject();
            System.out.println(e);

            // 多个对象需要序列化，可以将对象放到集合中
            List<Employee> list = new ArrayList<>();
            list.add(new Employee(1, "as", 22, 3333));
            list.add(new Employee(2, "df", 33, 2232));
            list.add(new Employee(3, "ax", 44, 3434));
            objectOutputStream.writeObject(list);

            List<Employee> list2 = (ArrayList<Employee>) objectInputStream.readObject();
            list2.forEach(System.out::println);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
