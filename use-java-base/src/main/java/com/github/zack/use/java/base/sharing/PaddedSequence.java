package com.github.zack.use.java.base.sharing;

import sun.misc.Unsafe;

/**
 * 手动填充
 *
 * @author zack
 * @since 2024/12/13
 */
public class PaddedSequence {

    // 前置填充字段
    private volatile long p1, p2, p3, p4, p5, p6, p7;

    // 核心变量
    private volatile long value;

    // 后置填充字段
    private volatile long p8, p9, p10, p11, p12, p13, p14, p15;

    public PaddedSequence(long initialValue) {
        this.value = initialValue;
    }

    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value;
    }

    public boolean compareAndSet(long expectedValue, long newValue) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, VALUE_OFFSET, expectedValue, newValue);
    }

    private static final long VALUE_OFFSET;

    static {
        try {
            VALUE_OFFSET = UnsafeAccess.UNSAFE.objectFieldOffset(
                    PaddedSequence.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
