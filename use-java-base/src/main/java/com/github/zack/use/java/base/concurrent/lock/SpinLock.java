package com.github.zack.use.java.base.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * 自旋锁简单demo
 * 自旋锁适用于锁竞争不激烈、锁持有时间短的场景
 *
 * @author zack
 * @since 2024/11/25
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    public <T> void lock(Consumer<T> consumer, T t) {

        Thread currentThread = Thread.currentThread();

        while (!owner.compareAndSet(null, currentThread)) {
            //...
            consumer.accept(t);
        }

    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        owner.compareAndSet(currentThread, null);
    }
}
