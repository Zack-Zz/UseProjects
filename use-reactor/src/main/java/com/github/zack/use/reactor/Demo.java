package com.github.zack.use.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author zhouze
 * @date 2022/8/7
 */
public class Demo {

    public static void main(String[] args) {
        Flux<Integer> ints = Flux.range(1, 12);
//        ints.subscribe(System.out::println,
//                error -> System.err.println("Error " + error),
//                () -> System.out.println("Done"),
//                sub -> {
//                    System.out.println("sub request 10. " + System.currentTimeMillis());
//                    sub.request(Integer.MAX_VALUE);
//                    System.out.println("sub request 20. " + System.currentTimeMillis());
//                });
//
//        Flux<String> flux = Flux.generate(
//                () -> 0,
//                (state, sink) -> {
//                    sink.next("3 x " + state + " = " + 3 * state);
//                    if (state == 10) sink.complete();
//                    return state + 1;
//                });
//
//        SampleBaseSubscriber<String> ss = new SampleBaseSubscriber<>();
//        flux.doOnRequest(x -> {
//                    System.out.println("request:" + x);
//                })
//                .subscribe(ss);

        System.out.println("run start. Thread:" + Thread.currentThread().getName());

        Scheduler scheduler = Schedulers.newParallel("parallel-scheduler", 4);

//        final Flux<String> flux = Flux
//                .range(1, 2)
//                .map(i -> {
//                    System.out.println("first map. i:" + i + " thread:" + Thread.currentThread().getName());
//                    return 10 + i;
//                })
//                .publishOn(scheduler)
//                .map(i -> {
//                    System.out.println("second map. i:" + i + " thread:" + Thread.currentThread().getName());
//                    return "value " + i;
//                });

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> {
                    System.out.println("first map. i:" + i + " thread:" + Thread.currentThread().getName());
                    return 10 + i;
                })
                .subscribeOn(scheduler)
                .map(i -> {
                    System.out.println("second map. i:" + i + " thread:" + Thread.currentThread().getName());
                    return "value " + i;
                });

        new Thread(() -> {
            flux.subscribe(x -> {
                System.out.println("subscribe value: " + x + " . thread name: " + Thread.currentThread().getName());
            });
        }).start();

    }
}
