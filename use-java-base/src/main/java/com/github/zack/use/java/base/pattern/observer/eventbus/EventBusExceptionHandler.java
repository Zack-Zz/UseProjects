package com.github.zack.use.java.base.pattern.observer.eventbus;

/**
 * 异常处理
 *
 * @author zack
 * @since 2024/12/14
 */
public interface EventBusExceptionHandler {
    void handleException(Throwable exception, Object event, EventBus.Subscriber subscriber);
}
