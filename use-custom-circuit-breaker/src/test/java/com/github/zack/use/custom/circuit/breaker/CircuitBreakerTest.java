package com.github.zack.use.custom.circuit.breaker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.io.IOException;

/**
 * @author zhouze
 * @date 2023/10/19
 */
public class CircuitBreakerTest {

    @Order(1)
    @DisplayName("test run function")
    @Test
    void runTest() throws IOException {
        CircuitBreaker circuitBreaker = new CircuitBreaker(new Config());
        String okName = circuitBreaker.run(() -> {
            return "ok";
        }, t -> {
            return "fail";
        });
        System.out.println(okName);
    }

    @Order(2)
    @DisplayName("test run error")
    @Test
    void runErrorTest() throws IOException {
        CircuitBreaker circuitBreaker = new CircuitBreaker(new Config());

        String okName = circuitBreaker.run(() -> {
            throw new RuntimeException("run found error");
        }, t -> {
            return "fail";
        });
        System.out.println(okName);
    }
}
