package com.github.zack.use.java.base.pattern.observer;

/**
 * @author zack
 * @since 2024/12/14
 */
public class ObserverPatternMain {
    public static void main(String[] args) {
        Subject subject = new Subject();

        // 添加观察者（使用 Lambda 表达式）
        subject.addObserver(message -> System.out.println("Observer 1 received: " + message));
        subject.addObserver(message -> System.out.println("Observer 2 received: " + message));

        // 通知观察者
        subject.notifyObservers("Hello, Observers!");
    }
}
