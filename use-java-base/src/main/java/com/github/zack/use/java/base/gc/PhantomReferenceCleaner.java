package com.github.zack.use.java.base.gc;

import com.github.zack.use.java.base.demo.Resource;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zack
 * @since 2024/12/1
 */
public class PhantomReferenceCleaner implements Runnable {


    private final ReferenceQueue<Object> queue = new ReferenceQueue<>();
    private final ConcurrentMap<PhantomReference<Object>, Resource> references = new ConcurrentHashMap<>();

    /**
     * 跟踪对象，该对象与Resource绑定,意味着该对象GC时，也会清理相应的Resource
     *
     * @param obj
     * @param resource
     */
    public void track(Object obj, Resource resource) {
        PhantomReference<Object> ref = new PhantomReference<>(obj, queue);
        references.put(ref, resource);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 阻塞等待JVM对该队列的通知
                PhantomReference<?> ref = (PhantomReference<?>) queue.remove();
                Resource resource = references.remove(ref);
                if (resource != null) {
                    resource.cleanup();
                }
                ref.clear();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
