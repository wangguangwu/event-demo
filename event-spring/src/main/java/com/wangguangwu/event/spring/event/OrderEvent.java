package com.wangguangwu.event.spring.event;

import com.wangguangwu.event.spring.enums.OrderStatus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 订单事件
 *
 * @author wangguangwu
 */
@Getter
public class OrderEvent extends ApplicationEvent {

    private final String orderId;
    private final OrderStatus orderStatus;

    public OrderEvent(Object source, String orderId, OrderStatus orderStatus) {
        super(source);
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
