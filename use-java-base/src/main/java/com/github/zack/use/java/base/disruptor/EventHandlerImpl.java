package com.github.zack.use.java.base.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author zack
 * @since 2024/12/13
 */
public class EventHandlerImpl implements EventHandler<Event> {

    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "[START] Processing1 event: " + event.getValue());
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "[END] Processing1 event: " + event.getValue());
    }
}
