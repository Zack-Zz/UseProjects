package com.github.zack.use.java.base.sharing;

/**
 * 未填充
 *
 * @author zack
 * @since 2024/12/13
 */
public class UnPaddingSequence {

    private volatile long value;

    public UnPaddingSequence(long initialValue) {
        this.value = initialValue;
    }

    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value;
    }

}
