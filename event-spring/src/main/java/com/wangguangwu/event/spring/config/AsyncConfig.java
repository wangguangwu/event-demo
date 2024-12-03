package com.wangguangwu.event.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步配置
 *
 * @author wangguangwu
 */
@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean("eventTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(2);
        // 最大线程数
        executor.setMaxPoolSize(5);
        // 队列容量
        executor.setQueueCapacity(10);
        // 线程名称前缀
        executor.setThreadNamePrefix("event-async-");
        // 初始化
        executor.initialize();
        return executor;
    }
}
