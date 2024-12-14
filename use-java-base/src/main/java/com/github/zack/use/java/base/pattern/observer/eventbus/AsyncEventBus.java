package com.github.zack.use.java.base.pattern.observer.eventbus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zack
 * @since 2024/12/14
 */
public class AsyncEventBus extends EventBus {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void post(Object event) {
        executor.submit(() -> super.post(event)); // 异步发布事件
    }

}
