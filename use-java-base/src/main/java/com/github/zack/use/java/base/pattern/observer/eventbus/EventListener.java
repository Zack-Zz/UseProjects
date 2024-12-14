package com.github.zack.use.java.base.pattern.observer.eventbus;

/**
 * @author zack
 * @since 2024/12/14
 */
public class EventListener {

    @Subscribe
    public void onMessageReceived(MessageEvent event) {
        System.out.println(Thread.currentThread().getName() + ". Received message: " + event.getMessage());

        throw new RuntimeException("RUNTIME test ex.");
    }
}
