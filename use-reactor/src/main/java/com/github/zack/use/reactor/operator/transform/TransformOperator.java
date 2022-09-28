package com.github.zack.use.reactor.operator.transform;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author zhouze
 * @date 2022/8/9
 */
public class TransformOperator {

    /**
     * 封装成函数
     *
     * @return
     */
    public static Function<Flux<String>, Flux<String>> filterAndMap() {
        return f -> f.filter(color -> !color.equals("orange"))
                .map(String::toUpperCase);
    }

    /**
     * use filterAndMap() by transform
     *
     * @param args
     */
    public static void main(String[] args) {
        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .doOnNext(System.out::println)
                .transform(filterAndMap())
                .subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: " + d));
    }
}
