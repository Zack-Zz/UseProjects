package com.github.zack.use.bytebuddy.aop.advice;

import net.bytebuddy.asm.Advice;

/**
 * @author zack
 * @since 2024/12/6
 */
public class OrderAdvice {

    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Argument(0) String orderId) {
        System.out.println("Before processing order: " + orderId);
    }

    @Advice.OnMethodExit
    public static void onExit() {
        System.out.println("After processing order");
    }
}
