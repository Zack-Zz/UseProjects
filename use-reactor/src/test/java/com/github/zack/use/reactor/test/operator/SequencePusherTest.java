package com.github.zack.use.reactor.test.operator;

import com.github.zack.use.reactor.operator.push.SequencePusher;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * @author zhouze
 * @date 2022/8/8
 */
public class SequencePusherTest {

    @Test
    void testCreateToWrapMultiThreadsAsyncExternalAPI() {
        SequencePusher sequencePusher = new SequencePusher();
        int numberOfElements = 10000;

        StepVerifier.create(sequencePusher.pushNumberSequence(numberOfElements))
                .expectNextCount(numberOfElements)
                .expectErrorMessage("expected count error")
                .verify();
    }
}
