package com.wangguangwu.event.spring.listener;

import com.wangguangwu.event.spring.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 订单邮件通知监听器
 * 只处理订单创建的事件
 *
 * @author wangguangwu
 */
@Slf4j
@Component
public class EmailOrderEventListener {

    @Order(1)
    @Async("eventTaskExecutor")
    @EventListener(condition = "#root.args[0].orderStatus == T(com.wangguangwu.event.spring.enums.OrderStatus).CREATED")
    public void handleOrderEvent(OrderEvent event) {
        log.info("Email notification - Processing order event: orderId={}, status={}",
                event.getOrderId(), event.getOrderStatus());

        // 模拟发送邮件的耗时操作
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("Email notification - Finished processing order event: orderId={}, status={}",
                event.getOrderId(), event.getOrderStatus());
    }
}
