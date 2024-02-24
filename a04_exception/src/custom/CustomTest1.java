package custom;

import org.junit.Test;

/**
 * 自定义异常类
 *
 * @author zt
 * @date 2023/7/19
 **/
public class CustomTest1 {

    class MyException extends Exception {
        static final long serialVersionUID = 23423423435L;
        private int idNumber;

        public MyException(String message, int id) {
            super(message);
            this.idNumber = id;
        }

        public int getId() {
            return idNumber;
        }
    }

    public void regist(int num) throws MyException {
        if (num < 0) {
            throw new MyException("人数为负值，不合理", 3);
        } else {
            System.out.println("登记人数" + num);
        }
    }

    @Test
    public void test1() {
        try {
            regist(-100);
        } catch (MyException e) {
            System.out.print("登记失败，出错种类" + e.getId());
        }
        System.out.print("本次登记操作结束");
    }
}
