package com.github.zack.use.java.base.pattern.observer.event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * @author zack
 * @since 2024/12/13
 */
public class EventBus {

    private final ConcurrentHashMap<Class<?>, CopyOnWriteArrayList<Consumer<?>>> subscribers = new ConcurrentHashMap<>();

    // 注册订阅者
    public <T> void subscribe(Class<T> eventType, Consumer<T> handler) {
        subscribers.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(handler);
    }

    // 发布事件
    public <T> void post(T event) {
        Class<?> eventType = event.getClass();
        CopyOnWriteArrayList<Consumer<?>> handlers = subscribers.get(eventType);
        if (handlers != null) {
            for (Consumer<?> handler : handlers) {
                @SuppressWarnings("unchecked")
                Consumer<T> consumer = (Consumer<T>) handler;
                consumer.accept(event);
            }
        }
    }

}
