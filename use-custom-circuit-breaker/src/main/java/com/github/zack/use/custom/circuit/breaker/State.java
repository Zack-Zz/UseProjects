package com.github.zack.use.custom.circuit.breaker;

/**
 * 熔断器枚举
 *
 * @author zhouze
 * @date 2023/10/19
 */
public enum State {

    /**
     * 关闭
     */
    CLOSED,
    /**
     * 半开
     */
    HALF_OPEN,
    /**
     * 打开
     */
    OPEN;
}
