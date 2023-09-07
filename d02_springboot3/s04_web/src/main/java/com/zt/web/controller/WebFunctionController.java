package com.zt.web.controller;

import com.zt.web.bean.Person;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zt
 * @since 2023/8/30 19:02
 **/
@Slf4j
@RestController
public class WebFunctionController {

    public ServerResponse getUsers(ServerRequest request) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "zt", "123@qq.com", 20, "common"));
        personList.add(new Person(2L, "zt2", "222@qq.com", 20, "common"));
        log.info("查询所有用户信息完成");
        return ServerResponse.ok().body(personList);
    }

    public ServerResponse getUser(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("查询 【{}】 用户信息，数据库正在检索", id);
        return ServerResponse.ok().body(new Person(1L, "zt", "123@qq.com", 20, "common"));
    }

    public ServerResponse createUser(ServerRequest request) throws ServletException, IOException {
        // 提取请求体
        Person body = request.body(Person.class);
        log.info("保存用户信息：{}", body);
        return ServerResponse.ok().build();
    }

    public ServerResponse updateUser(ServerRequest request) throws ServletException, IOException {
        Person body = request.body(Person.class);
        log.info("保存用户信息更新: {}", body);
        return ServerResponse.ok().build();
    }

    public ServerResponse deleteUser(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("删除【{}】用户信息", id);
        return ServerResponse.ok().build();
    }
}
