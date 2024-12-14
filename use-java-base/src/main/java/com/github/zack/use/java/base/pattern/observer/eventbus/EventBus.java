package com.github.zack.use.java.base.pattern.observer.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zack
 * @since 2024/12/14
 */
public class EventBus {
    // 存储事件类型到订阅者的映射
    private final Map<Class<?>, List<Subscriber>> subscribers = new ConcurrentHashMap<>();

    private EventBusExceptionHandler exceptionHandler = (exception, event, subscriber) -> {
        System.err.println(Thread.currentThread().getName() + ". Error handling event: " +
                (Objects.isNull(exception.getCause()) ?
                        exception.getMessage() :
                        exception.getCause().getMessage()));
    };

    public void setExceptionHandler(EventBusExceptionHandler handler) {
        this.exceptionHandler = handler;
    }

    // 注册订阅者
    public void register(Object listener) {
        // 获取订阅者类的所有方法
        Method[] methods = listener.getClass().getDeclaredMethods();
        for (Method method : methods) {
            // 如果方法被 @Subscribe 注解
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?> eventType = method.getParameterTypes()[0]; // 方法的第一个参数作为事件类型
                subscribers.computeIfAbsent(eventType, k -> new ArrayList<>())
                        .add(new Subscriber(listener, method));
            }
        }
    }

    // 发布事件
    public void post(Object event) {
        Class<?> eventType = event.getClass();
        // 找到该事件类型的所有订阅者
        List<Subscriber> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            for (Subscriber subscriber : eventSubscribers) {
                try {
                    subscriber.invoke(event); // 调用订阅者方法
                } catch (Exception e) {
//                    e.printStackTrace(); // 简单处理异常

                    exceptionHandler.handleException(e, event, subscriber);
                }
            }
        }
    }

    // 内部类：订阅者
    public static class Subscriber {
        private final Object listener;
        private final Method method;

        Subscriber(Object listener, Method method) {
            this.listener = listener;
            this.method = method;
            this.method.setAccessible(true); // 允许调用私有方法
        }

        void invoke(Object event) throws Exception {
            method.invoke(listener, event); // 反射调用订阅方法
        }
    }
}
