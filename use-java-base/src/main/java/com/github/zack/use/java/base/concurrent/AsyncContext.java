package com.github.zack.use.java.base.concurrent;

import java.util.Objects;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * @author zack
 * @since 2025/4/17
 */
public class AsyncContext<T> {

    private T data;

    private Thread thread;


    public void startAsync(){
        thread = Thread.currentThread();
    }

    public void writeData(T pData) {
        data = pData;
        LockSupport.unpark(thread);
    }

    public T get() {
        while (Objects.isNull(data)) {
            LockSupport.park();
        }
        return data;
    }
}
