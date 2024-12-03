package com.wangguangwu.event.spring.listener;

import com.wangguangwu.event.spring.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 订单日志记录监听器
 *
 * @author wangguangwu
 */
@Slf4j
@Component
public class LogOrderEventListener {

    @Order(2)
    @Async("eventTaskExecutor")
    @EventListener
    public void handleOrderEvent(OrderEvent event) {
        log.info("Log system - Processing order event: orderId={}, status={}", 
                event.getOrderId(), event.getOrderStatus());
        
        // 模拟记录日志的耗时操作
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        log.info("Log system - Finished processing order event: orderId={}, status={}", 
                event.getOrderId(), event.getOrderStatus());
    }
}
