package com.wangguangwu.event.spring.service;

import com.wangguangwu.event.spring.enums.OrderStatus;
import com.wangguangwu.event.spring.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 订单服务
 *
 * @author wangguangwu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApplicationEventPublisher eventPublisher;

    public void createOrder(String orderId) {
        // 模拟订单创建逻辑
        log.info("Creating order with ID: {}", orderId);
        
        // 发布订单创建事件
        OrderEvent orderEvent = new OrderEvent(this, orderId, OrderStatus.CREATED);
        eventPublisher.publishEvent(orderEvent);
        log.info("Order created event published for order ID: {}", orderId);
    }

    public void completeOrder(String orderId) {
        // 模拟订单完成逻辑
        log.info("Completing order with ID: {}", orderId);
        
        // 发布订单完成事件
        eventPublisher.publishEvent(new OrderEvent(this, orderId, OrderStatus.COMPLETED));
        log.info("Order completed event published for order ID: {}", orderId);
    }

    public void failOrder(String orderId) {
        // 模拟订单失败逻辑
        log.info("Failing order with ID: {}", orderId);
        
        // 发布订单失败事件
        eventPublisher.publishEvent(new OrderEvent(this, orderId, OrderStatus.FAILED));
        log.info("Order failed event published for order ID: {}", orderId);
    }
}
