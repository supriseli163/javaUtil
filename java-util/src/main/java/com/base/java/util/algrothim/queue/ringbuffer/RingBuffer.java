package com.base.java.util.algrothim.queue.ringbuffer;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
/**
 * The semantic of @ThreadSafe syntax is to simple declare a Type is Thread-Safe to user.
 * Regardless of the interleaving of those actions by runtime, and without
 * to check the class is do ThreadSafe.
 * It may be a bad idea.
 * @param <T>
 */
@ThreadSafe
public class RingBuffer<T> {
    /**the slot is written*/
    private AtomicInteger write_cursor;
    /**slot in ring buffer which is read*/
    private AtomicInteger read_cursor;

    private boolean safe = true;
    private T[] buffer;
    private int size;

    public RingBuffer(int size) {

    }

    public void checkInitSize() {
        if(size == 1 || (size & -size) != size) {
            throw new IllegalArgumentException("ring buffer size must be Power Of ");
        }
    }



    public void initEnv() {
        this.write_cursor = new AtomicInteger(-1);
        this.read_cursor = new AtomicInteger(-1);
    }

    /**
     * push a T element into buffer, if the buffer is full, it will block until the buffer can insert element
     * @param t
     */
    public void push(T t) {
        do {
            /**already write*/
            int cur = write_cursor.get();
            /**next to write*/
            int next = (cur + 1) & (size - 1);
            int r_cur = read_cursor.get();
            if ((r_cur == -1 && (size - 1) == next) || next == r_cur) {
                LockSupport.parkNanos(1);
            } else {
                if(write_cursor.compareAndSet(cur, next)) {
                    buffer[next] = t;
                    break;
                }
            }
        } while (true);
    }

    /**
     * top - - - - - -- - - tail
     * pop the top element of buffer, Null will be return if the buffer size is 0.
     * @return
     */
    public T pop() {
        T t = null;
        int start, end, index;
        do {
            start = read_cursor.get();
            end = read_cursor.get() + 1;
            t = (T)buffer[start];
        } while (!read_cursor.compareAndSet(start, end));
        return t;
    }

    /**
     * check the index is available
     * @param index
     * @return
     */
    public boolean checkIndex(int index) {
        if(index > (buffer.length - 1) || index == -1) {
            System.out.println("the index should be in 0 and " + (size - 1));
            throw new IllegalArgumentException("the index should be in 0 and " + (size - 1));
        }
        return true;
    }

    public T getElement(int index) throws NullPointerException {
        checkIndex(index);
        return buffer[index];
    }

    public T[] getElement(int start, int end) {
        checkIndex(start);
        checkIndex(end);

        if(start >= end) {
            throw new IllegalArgumentException(String.format("The start index should be little than end index"));
        } else {
            return Arrays.copyOfRange(buffer, start, end);
        }
    }

    public List<T> popAll() {
        int max = 32 < size ? 32 : size;
        List<T> result = new ArrayList<>(max);
        T t = null;
        while (result.size() < max && (t = pop()) != null)
            result.add(t);
        return result.size() == 0 ? null : result;
    }
}
