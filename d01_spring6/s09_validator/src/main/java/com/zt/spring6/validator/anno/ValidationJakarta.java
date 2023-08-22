package com.zt.spring6.validator.anno;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 使用 jakarta.validation.Validator 校验
 *
 * @author zt
 * @since 2023/8/22 20:49
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
