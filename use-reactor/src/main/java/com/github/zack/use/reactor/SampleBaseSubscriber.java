package com.github.zack.use.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @author zhouze
 * @date 2022/8/7
 */
public class SampleBaseSubscriber<T> extends BaseSubscriber<T> {

    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("hookOnSubscribe:Subscribed");
        request(2);
//        requestUnbounded();
        System.out.println("hookOnSubscribe: request complete");
    }

    public void hookOnNext(T value) {
        System.out.println("hookOnNext:" + value);
        request(1);
    }
}
