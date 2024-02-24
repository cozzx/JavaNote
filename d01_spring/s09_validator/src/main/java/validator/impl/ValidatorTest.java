package validator.impl;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

/**
 * @author zt
 * @date 2023/8/22
 **/
public class ValidatorTest {

    @Test
    public void test() {
        Person person = new Person();
        person.setName("lucy");
        person.setAge(250);

        // 创建 person 对应 DataBinder
        DataBinder dataBinder = new DataBinder(person);
        // 设置校验器
        dataBinder.setValidator(new PersonValidator());
        // 执行校验
        dataBinder.validate();
        // 校验结果
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult.getAllErrors());
    }
}
