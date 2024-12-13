package com.github.zack.use.java.base.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author zack
 * @since 2024/12/13
 */
public class EventFactoryImpl implements EventFactory<Event> {


    @Override
    public Event newInstance() {
        return new Event(); // 每个槽位的初始对象
    }
}
