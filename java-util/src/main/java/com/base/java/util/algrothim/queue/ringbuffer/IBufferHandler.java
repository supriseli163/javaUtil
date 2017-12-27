package com.base.java.util.algrothim.queue.ringbuffer;
import java.util.List;
public interface IBufferHandler<T> {
    boolean enableBatchHandle();

    void handle(T t) throws Throwable;

    void handle(List<T> t) throws Throwable;
}
