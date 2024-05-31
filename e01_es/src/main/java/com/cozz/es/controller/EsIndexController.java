package com.cozz.es.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2024/4/6
 **/
@RestController
@RequestMapping("hotel")
@Tag(name = "es 索引操作", description = "测试 elasticsearch 索引操作")
public class EsIndexController {

    @GetMapping("add")
    @Operation(summary = "添加索引库")
    public void add() {
    }
}
