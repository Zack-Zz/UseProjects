package com.github.zack.use.java.base.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 *
 * @author zack
 * @since 2025/4/9
 */
public class JMXMain {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        ManagementFactory.getPlatformMBeanServer().registerMBean(
                new AppCount(), new ObjectName("com.github.zack.use.java.base.jmx:type=AppCountMXBean")
        );

        Thread.sleep(1000 * 60 * 60);
    }
}
