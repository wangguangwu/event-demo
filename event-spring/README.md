# Event Spring Demo

这是一个使用 Spring 事件机制的示例项目，展示了如何使用 Spring 的事件发布和监听功能来实现订单状态变更的异步通知。

## 功能特性

- 基于 Spring Event 的事件驱动架构
- 异步事件处理
- 订单状态管理（创建、完成、失败）
- 邮件通知（仅处理订单创建事件）
- 日志记录（处理所有订单事件）

## 技术栈

- Spring Boot
- Spring Event
- Lombok
- JDK 17

## 项目结构

```
event-spring/
├── src/
│   └── main/
│       └── java/
│           └── com/wangguangwu/event/spring/
│               ├── config/
│               │   └── AsyncConfig.java           # 异步线程池配置
│               ├── controller/
│               │   └── OrderController.java       # 订单接口控制器
│               ├── enums/
│               │   └── OrderStatus.java          # 订单状态枚举
│               ├── event/
│               │   └── OrderEvent.java           # 订单事件定义
│               ├── listener/
│               │   ├── EmailOrderEventListener.java # 邮件通知监听器（仅处理创建事件）
│               │   └── LogOrderEventListener.java   # 日志记录监听器（处理所有事件）
│               ├── service/
│               │   └── OrderService.java          # 订单服务
│               └── EventSpringApplication.java    # 应用程序入口
```

## 核心组件说明

### 1. 异步配置 (AsyncConfig)
- 配置自定义线程池用于异步事件处理
- 核心线程数：2
- 最大线程数：5
- 队列容量：10

### 2. 事件监听器
- EmailOrderEventListener：
  * 仅处理订单创建事件
  * 异步处理
  * 优先级：1（@Order(1)）
  
- LogOrderEventListener：
  * 处理所有订单事件
  * 异步处理
  * 优先级：2（@Order(2)）

### 3. 订单状态
- CREATED：订单创建
- COMPLETED：订单完成
- FAILED：订单失败

## API 接口

### 创建订单
```bash
curl -X POST http://localhost:8080/orders/create/123
```

### 完成订单
```bash
curl -X POST http://localhost:8080/orders/complete/123
```

### 失败订单
```bash
curl -X POST http://localhost:8080/orders/fail/123
```

## 事件处理流程

1. 客户端调用 OrderController 的接口
2. OrderService 处理业务逻辑并发布对应的 OrderEvent
3. Spring 将事件异步分发给相应的监听器：
   - 创建事件：由 EmailOrderEventListener 和 LogOrderEventListener 处理
   - 完成事件：仅由 LogOrderEventListener 处理
   - 失败事件：仅由 LogOrderEventListener 处理
4. 监听器在独立的线程中执行各自的处理逻辑

## 最佳实践

1. 使用枚举管理订单状态，确保类型安全
2. 使用 @EventListener 的 condition 属性进行事件过滤
3. 通过 @Order 注解控制监听器执行顺序
4. 使用自定义线程池进行异步处理
5. 统一的日志记录方案

## 扩展建议

1. 添加更多的订单状态
2. 实现真实的邮件发送功能
3. 添加数据持久化
4. 增加事务管理
5. 添加更多的事件监听器
6. 实现更复杂的业务规则
