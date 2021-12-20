package com.github.zack.use.projects.grpc.client;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.Channel;

import java.util.Random;
import java.util.logging.Logger;

/**
 * @author zack
 * @date 2021/12/20
 */
public class RouteGuideClient {
    private static final Logger logger = Logger.getLogger(RouteGuideClient.class.getName());

    private final RouteGuideGrpc.RouteGuideBlockingStub blockingStub;
    private final RouteGuideGrpc.RouteGuideStub asyncStub;

    private Random random = new Random();
    private TestHelper testHelper;

    public RouteGuideClient(Channel channel) {
        blockingStub = RouteGuideGrpc.newBlockingStub(channel);
        asyncStub = RouteGuideGrpc.newStub(channel);
    }

    @VisibleForTesting
    void setTestHelper(TestHelper testHelper) {
        this.testHelper = testHelper;
    }

    @VisibleForTesting
    void setRandom(Random random) {
        this.random = random;
    }
}
