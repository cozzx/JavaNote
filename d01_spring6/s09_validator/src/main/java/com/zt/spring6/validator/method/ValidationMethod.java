package com.zt.spring6.validator.method;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author zt
 * @since 2023/8/22 21:06
 **/
@Service
@Validated
public class ValidationMethod {

    public String validator(@NotNull @Valid User user) {
        return user.toString();
    }
}
