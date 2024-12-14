package com.github.zack.use.java.base.pattern.observer.eventbus;

/**
 * @author zack
 * @since 2024/12/14
 */
public class EventBusMain {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        EventBus asyncEventBus = new AsyncEventBus();

        // 注册订阅者
        EventListener listener = new EventListener();
        eventBus.register(listener);
        asyncEventBus.register(listener);

        // 发布事件
        eventBus.post(new MessageEvent("Hello EventBus!"));
        asyncEventBus.post(new MessageEvent("Hello EventBus!"));
    }
}
