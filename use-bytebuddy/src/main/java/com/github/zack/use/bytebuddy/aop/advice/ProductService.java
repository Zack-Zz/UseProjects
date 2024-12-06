package com.github.zack.use.bytebuddy.aop.advice;

/**
 * @author zack
 * @since 2024/12/6
 */
public class ProductService {
    public void processOrder(String orderId) {
        System.out.println("Processing order: " + orderId);
    }
}
