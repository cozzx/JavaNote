package validator.anno;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 使用 jakarta.validation.Validator 校验
 *
 * @author zt
 * @date 2023/8/22
 **/
@Service
public class ValidationJakarta {

    @Autowired
    private Validator validator;

    public boolean validator(User user) {
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        return validate.isEmpty();
    }
}
