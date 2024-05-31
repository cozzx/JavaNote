package com.cozz.seata.controller;

import com.cozz.common.util.Result;
import com.cozz.seata.serivce.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @date 2024/3/10
 **/

@RestController
public class AccountController {

    @Resource
    AccountService accountService;
    /**
     * 扣减账户余额
     */
    @PostMapping("/account/decrease")
    public Result decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money){
        accountService.decrease(userId,money);
        return Result.ok("扣减账户余额成功！");
    }
}
