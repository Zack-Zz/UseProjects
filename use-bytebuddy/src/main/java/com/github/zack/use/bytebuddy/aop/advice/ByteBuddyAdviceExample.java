package com.github.zack.use.bytebuddy.aop.advice;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author zack
 * @since 2024/12/6
 */
public class ByteBuddyAdviceExample {

    public static void main(String[] args) throws Exception {
        // 动态代理目标类
        Class<?> proxyClass = new ByteBuddy()
                .subclass(ProductService.class) // 创建 ProductService 的子类
                .method(ElementMatchers.named("processOrder")) // 匹配方法 processOrder
                .intercept(Advice.to(OrderAdvice.class)) // 使用 Advice 实现增强逻辑
                .make()
                .load(ProductService.class.getClassLoader()) // 加载到 JVM
                .getLoaded();

        // 创建代理实例并调用方法
        ProductService productService = (ProductService) proxyClass.getDeclaredConstructor().newInstance();
        productService.processOrder("12345");
    }


}
