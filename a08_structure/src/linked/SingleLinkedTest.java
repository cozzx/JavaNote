package linked;

import org.junit.Test;

/**
 * 单链表测试
 *
 * @author zt
 * @since 2023/7/3 21:58
 **/
public class SingleLinkedTest {

    @Test
    public void test1() {
        SingleLinkedCustom<String> singleLinkedCustom = new SingleLinkedCustom<>();
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
