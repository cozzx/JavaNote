package linked;

import org.junit.Test;

/**
 * 单链表测试
 *
 * @author zt
 * @date 2023/7/3 21:58
 **/
public class DoubleLinkedTest {

    @Test
    public void test1() {
        DoubleLinkedCustom<String> singleLinkedCustom = new DoubleLinkedCustom<>();
        singleLinkedCustom.add("123");
        singleLinkedCustom.add("234");
        singleLinkedCustom.add("345");
        singleLinkedCustom.add("456");
        singleLinkedCustom.add("567");

        singleLinkedCustom.print();
        System.out.println(singleLinkedCustom.contains("567"));
        System.out.println(singleLinkedCustom.modify("123", "111"));
        System.out.println(singleLinkedCustom.remove("345"));
        singleLinkedCustom.print();
    }
}
