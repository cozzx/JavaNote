package linked;

/**
 * 自定义单链表
 *
 * @author zt
 * @since 2023/7/3 21:23
 **/
public class SingleLinkedCustom<E> {

    // 节点类
    class Node {

        E data;

        Node node;

        public Node(E e, Node n) {
            data = e;
            node = n;
        }
    }

    // 头结点
    private Node header;

    private int size;

    /**
     * 添加元素
     */
    public void add(E e) {
        Node newNode = new Node(e, null);
        if (header == null) {
            header = newNode;
        } else {
            Node endNode = findEnd(header);
            endNode.node = newNode;
        }
        size++;
    }

    /**
     * 查找链表最后一个元素
     */
    private Node findEnd(Node node) {
        if (node.node == null) {
            return node;
        }
        return findEnd(node.node);
    }

    /**
     * 查找元素是否存在
     */
    public boolean contains(E e) {
        if (header == null) {
            return false;
        }
        if (header.data.equals(e)) {
            return true;
        }
        Node curNode = header.node;
        while (curNode != null) {
            if (curNode.data.equals(e)) {
                return true;
            }
            curNode = curNode.node;
        }
        return false;
    }

    /**
     * 修改链表中某个数据
     */
    public boolean modify(E o, E n) {
        if (header == null) {
            return false;
        }
        if (header.data.equals(o)) {
            header.data = n;
            return true;
        }
        Node curNode = header.node;
        while (curNode != null) {
            if (curNode.data.equals(o)) {
                curNode.data = n;
                return true;
            }
            curNode = curNode.node;
        }
        return false;
    }

    /**
     * 删除链表中某个元素的方法
     */
    public boolean remove(E e) {
        if (header == null) {
            return false;
        }
        if (header.data.equals(e)) {
            header = header.node;
            size--;
            return true;
        }
        Node preNode = header;
        Node curNode = header.node;
        while (curNode != null) {
            if (curNode.data.equals(e)) {
                preNode.node = curNode.node;
                size--;
                return true;
            }
            preNode = curNode;
            curNode = curNode.node;
        }
        return false;
    }

    /**
     * 输出链表中所有数据
     */
    public void print() {
        StringBuilder stringBuilder = new StringBuilder("{");
        if (header != null) {
            stringBuilder.append(header.data);
        }
        Node curNode = header.node;
        while (curNode != null) {
            stringBuilder.append(",").append(curNode.data);
            curNode = curNode.node;
        }
        stringBuilder.append("}");
        System.out.println(stringBuilder);
    }
}
