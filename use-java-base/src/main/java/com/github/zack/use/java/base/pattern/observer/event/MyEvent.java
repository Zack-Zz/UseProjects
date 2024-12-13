package com.github.zack.use.java.base.pattern.observer.event;

/**
 * @author zack
 * @since 2024/12/13
 */
public class MyEvent {

    private final String message;

    public MyEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
