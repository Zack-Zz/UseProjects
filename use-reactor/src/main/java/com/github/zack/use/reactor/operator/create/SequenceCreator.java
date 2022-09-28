package com.github.zack.use.reactor.operator.create;

import com.github.zack.use.reactor.operator.MultiThreadSequence;
import reactor.core.publisher.Flux;

/**
 * @author zhouze
 * @date 2022/8/8
 */
public class SequenceCreator {

    public Flux<Integer> createNumberSequence(Integer elementsToEmit) {
        return Flux.create(sharedSink -> new MultiThreadSequence().completeEmitElements(elementsToEmit, sharedSink));
    }

}
