package com.github.zack.use.java.base.concurrent.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源分配器，其作用是管理多个线程对资源的并发访问。
 * 通过 synchronized 关键字和 wait/notifyAll 机制，确保线程安全地分配和归还资源。
 *
 * @author zack
 * @since 2024/11/27
 */
public class Allocator {

    /**
     * 存储当前已被占用的资源
     */
    private final List<Object> als = new ArrayList<>();

    public List<Object> getAls() {
        return als;
    }

    /**
     * 申请两个资源
     * <p>
     * 如果资源已被其他线程占用，当前线程会阻塞，直到资源可用
     *
     * @param from
     * @param to
     */
    synchronized void apply(Object from, Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        als.add(from);
        als.add(to);
    }

    /**
     * 归还两个资源
     * <p>
     * 归还后，会通知等待的线程重新尝试申请资源
     *
     * @param from
     * @param to
     */
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }


}
