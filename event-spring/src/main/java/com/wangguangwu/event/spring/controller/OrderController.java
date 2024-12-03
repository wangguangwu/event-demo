package com.wangguangwu.event.spring.controller;

import com.wangguangwu.event.spring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制器
 *
 * @author wangguangwu
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/{orderId}")
    public String createOrder(@PathVariable("orderId") String orderId) {
        orderService.createOrder(orderId);
        return "Order created: " + orderId;
    }

    @PostMapping("/complete/{orderId}")
    public String completeOrder(@PathVariable("orderId") String orderId) {
        orderService.completeOrder(orderId);
        return "Order completed: " + orderId;
    }

    @PostMapping("/fail/{orderId}")
    public String failOrder(@PathVariable("orderId") String orderId) {
        orderService.failOrder(orderId);
        return "Order failed: " + orderId;
    }
}
