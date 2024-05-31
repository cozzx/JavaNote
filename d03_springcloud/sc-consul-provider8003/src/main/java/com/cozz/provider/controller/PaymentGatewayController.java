package com.cozz.provider.controller;

import com.cozz.common.entity.Payment;
import com.cozz.common.util.Result;
import com.cozz.provider.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

/**
 * 测试 gateway 网关
 *
 * @author zt
 * @date 2024/3/1
 **/
@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentGatewayController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("gateway/get/{id}")
    public Result getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("获取结果：provider:" + payment);
        if (payment != null) {
            return Result.ok().data(payment)
                    .put("serverPort", serverPort);
        } else {
            return Result.err()
                    .put("serverPort", serverPort);
        }
    }

    @GetMapping("gateway/info")
    public Result getInfo() {
        return Result.ok().put("serverPort", serverPort);
    }

    @GetMapping(value = "gateway/filter")
    public Result getGatewayFilter(HttpServletRequest request) {
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("请求头名: " + headName + "; 请求头值: " + headValue);

            if (headName.equalsIgnoreCase("X-Request-cozz1")
                    || headName.equalsIgnoreCase("X-Request-cozz2")) {
                result = result + headName + "\t " + headValue + " ";
            }
        }
        System.out.println("=============================================");
        String customerId = request.getParameter("customerId");
        System.out.println("request Parameter customerId: " + customerId);

        String customerName = request.getParameter("customerName");
        System.out.println("request Parameter customerName: " + customerName);
        System.out.println("=============================================");

        return Result.ok("getGatewayFilter 过滤器 test： " + result)
                .put("serverPort", serverPort);
    }
}
