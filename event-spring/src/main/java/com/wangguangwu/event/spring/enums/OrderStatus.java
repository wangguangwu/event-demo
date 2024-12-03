package com.wangguangwu.event.spring.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author wangguangwu
 */
@Getter
public enum OrderStatus {
    
    CREATED("CREATED", "已创建"),
    COMPLETED("COMPLETED", "已完成"),
    FAILED("FAILED", "已失败");

    private final String code;
    private final String desc;

    OrderStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
