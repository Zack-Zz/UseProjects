package com.github.zack.use.reactor.operator.transform;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author zhouze
 * @date 2022/8/9
 */
public class TransformDeferredOperator {

    public static Function<Flux<String>, Flux<String>> filterAndMap(AtomicInteger ai) {
        return f -> {
            int i = ai.incrementAndGet();
            System.out.println(i);
            if (i == 1) {
                return f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
            }
            return f.filter(color -> !color.equals("purple"))
                    .map(String::toUpperCase);
        };
    }

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        Flux<String> composedFlux =
                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                        .doOnNext(System.out::println)
                        .transformDeferred(filterAndMap(ai));

        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :" + d));
        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: "+d));
    }
}
