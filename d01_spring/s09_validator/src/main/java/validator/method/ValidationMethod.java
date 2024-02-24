package validator.method;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author zt
 * @date 2023/8/22
 **/
@Service
@Validated
public class ValidationMethod {

    public String validator(@NotNull @Valid User user) {
        return user.toString();
    }
}
