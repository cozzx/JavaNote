package com.zt.web.config;

import com.zt.web.bean.Person;
import com.zt.web.controller.WebFunctionController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * 函数式web，定义路由
 *
 * @author zt
 * @since 2023/8/30 19:55
 **/
@Configuration
public class WebFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> userRoute(WebFunctionController webFunctionController) {
        return RouterFunctions.route()
                .GET("/users/test", RequestPredicates.accept(MediaType.ALL), request -> {
                    Person person = new Person(1L, "zt", "123@qq.com", 20, "common");
                    return ServerResponse.ok().body(person);
                })
                .GET("/users", RequestPredicates.accept(MediaType.ALL), webFunctionController::getUsers)
                .GET("/users/{id}", RequestPredicates.accept(MediaType.ALL), webFunctionController::getUser)
                .POST("/user", RequestPredicates.accept(MediaType.APPLICATION_JSON), webFunctionController::createUser)
                .PUT("/user/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), webFunctionController::updateUser)
                .DELETE("/user/{id}", webFunctionController::deleteUser)
                .build();
    }
}
