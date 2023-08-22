package com.zt.spring6.validator.anno;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * 使用 org.springframework.validation.Validator 校验
 *
 * @author zt
 * @since 2023/8/22 20:49
 **/
@Service
public class ValidationFramework {

    @Autowired
    private Validator validator;

    public boolean validator(User user) {
        BindException bindException = new BindException(user, user.getName());
        validator.validate(user, bindException);
        List<ObjectError> allErrors = bindException.getAllErrors();
        System.out.println(allErrors);
        return !bindException.hasErrors();
    }
}
