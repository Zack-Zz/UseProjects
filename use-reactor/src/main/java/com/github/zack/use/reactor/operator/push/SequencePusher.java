package com.github.zack.use.reactor.operator.push;

import com.github.zack.use.reactor.operator.MultiThreadSequence;
import reactor.core.publisher.Flux;

/**
 * @author zhouze
 * @date 2022/8/8
 */
public class SequencePusher {


    public Flux<Integer> pushNumberSequence(Integer elementsToEmit) {
        return Flux.push(sharedSink -> new MultiThreadSequence().completeEmitElements(elementsToEmit, sharedSink));
    }

}
