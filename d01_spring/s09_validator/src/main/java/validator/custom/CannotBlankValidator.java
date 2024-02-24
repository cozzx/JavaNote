package validator.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 自定义验证类
 *
 * @author zt
 * @date 2023/8/22
 **/
public class CannotBlankValidator implements ConstraintValidator<CannotBlank, String> {
    @Override
    public void initialize(CannotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // null时不进行校验
        if (s != null && s.contains(" ")) {
            // 获取默认提示信息
            String defaultConstraintMessageTemplate = constraintValidatorContext.getDefaultConstraintMessageTemplate();
            System.out.println("default message :" + defaultConstraintMessageTemplate);
            // 禁用默认提示信息
            constraintValidatorContext.disableDefaultConstraintViolation();
            // 设置提示语
            constraintValidatorContext.buildConstraintViolationWithTemplate("can not contains blank").addConstraintViolation();
            return false;
        }
        return true;
    }
}
