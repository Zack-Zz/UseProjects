package com.github.zack.use.bytebuddy.aop;

/**
 * @author zack
 * @since 2024/12/6
 */
public class LoggingInterceptor {

    public static String intercept(String arg0) throws Exception {
//        System.out.println("Before method: " + method.getName());
////        String result = (String) method.invoke(obj,args); // 调用目标方法
//        String result = callable.apply((String) args[0]);
//        System.out.println("After method: " + method.getName());
        System.out.println("arg0:" + arg0);
        return arg0;
    }

}
