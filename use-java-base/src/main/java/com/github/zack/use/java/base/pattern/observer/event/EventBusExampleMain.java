package com.github.zack.use.java.base.pattern.observer.event;

/**
 * @author zack
 * @since 2024/12/13
 */
public class EventBusExampleMain {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        // 注册订阅者
        eventBus.subscribe(MyEvent.class, event -> System.out.println("Received: " + event.getMessage()));

        // 发布事件
        eventBus.post(new MyEvent("Hello, custom EventBus!"));
    }

}
