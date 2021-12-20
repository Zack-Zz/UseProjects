package com.github.zack.use.projects.grpc.client;

import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.Message;

/**
 * @author zack
 * @date 2021/12/20
 */
@VisibleForTesting
public interface TestHelper {
    /**
     * Used for verify/inspect message received from server.
     */
    void onMessage(Message message);

    /**
     * Used for verify/inspect error received from server.
     */
    void onRpcError(Throwable exception);
}
