package com.cozz.seata.controller;

import com.cozz.common.util.Result;
import com.cozz.seata.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2024/3/10
 **/
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @PostMapping("/storage/decrease")
    public Result decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {

        storageService.decrease(productId, count);
        return Result.ok("扣减库存成功!");
    }
}

