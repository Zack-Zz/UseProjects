package com.github.zack.use.custom.circuit.breaker;

import lombok.Data;

/**
 * @author zhouze
 * @date 2023/10/19
 */
@Data
public class Config {

    /**
     * Closed 状态进入 Open状态 错误数阈值
     */
    private int failureCount = 5;

    /**
     * failureCount的统计时间窗口
     */
    private long failureTimeInterval = 2000;

    /**
     * Open进入Half-Open状态的超时时间
     */
    private int halfOpenTimeout = 5 * 1000;

    /**
     * halfOpen 进入Open状态的成功数阈值
     */
    private int halfOpenSuccessCount = 2;

}
