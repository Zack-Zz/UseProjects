package com.github.zack.use.java.base.concurrent.queue;

/**
 * @author zack
 * @since 2024/11/27
 */
public class Node<T> {

    private volatile int status;

    private T t;

    private Node<T> next;
    private Node<T> prev;

    public Node(T val) {
        this.status = 0;
        t = val;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
