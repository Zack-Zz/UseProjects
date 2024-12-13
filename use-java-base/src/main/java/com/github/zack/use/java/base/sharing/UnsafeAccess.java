package com.github.zack.use.java.base.sharing;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zack
 * @since 2024/12/13
 */
public class UnsafeAccess {

    public static final Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access Unsafe", e);
        }
    }
}
