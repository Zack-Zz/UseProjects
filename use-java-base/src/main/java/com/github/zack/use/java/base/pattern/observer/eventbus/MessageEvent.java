package com.github.zack.use.java.base.pattern.observer.eventbus;

/**
 * @author zack
 * @since 2024/12/14
 */
public class MessageEvent {
    private final String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
