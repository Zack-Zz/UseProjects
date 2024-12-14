package com.github.zack.use.java.base.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zack
 * @since 2024/12/14
 */
public class Subject {

    private final List<Observer> observers = new ArrayList<>();

    // 添加观察者
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // 通知所有观察者
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

}
