package com.github.zack.use.java.base.pattern.observer;

/**
 * @author zack
 * @since 2024/12/14
 */
@FunctionalInterface
public interface Observer {

    void update(String message);
}
