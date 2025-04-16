package com.github.zack.use.java.base.jmx;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 *
 * @author zack
 * @since 2025/4/9
 */
public class AppCount implements AppCountMXBean{

    private static ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    @Override
    public int getThreadsCount() {
        return threadMXBean.getThreadCount();
    }
}
