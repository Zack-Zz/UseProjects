package com.github.zack.use.reactor.test.operator;

import com.github.zack.use.reactor.operator.create.SequenceCreator;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * @author zhouze
 * @date 2022/8/8
 */
public class SequenceCreatorTest {

    @Test
    void testCreateToWrapMultiThreadsAsyncExternalAPI() {
        SequenceCreator sequenceCreator = new SequenceCreator();
        int numberOfElements = 10000;

        StepVerifier.create(sequenceCreator.createNumberSequence(numberOfElements))
                .expectNextCount(numberOfElements)
                .verifyComplete();
    }

}
