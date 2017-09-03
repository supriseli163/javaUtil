package com.base.data.access.alyer;

import com.sun.corba.se.spi.logging.LogWrapperFactory;

import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MulSemaphore {
   private final ConcurrentLinkedDeque<DALPermitsSet> dalPermitsSetPoll = new ConcurrentLinkedDeque<>();

    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();

    private final AtomicInteger[] semaphores;
    private final AtomicInteger[] decimalSempahores;

    public class DALPermitsSet {
        private final DALPermitsSet[] permitsSets
    }


    public class DALPermits {
        final public int id;
        final  public int permits;
        final private AtomicInteger semaphore;
        final private AtomicBoolean active = new AtomicBoolean(true);

        private DALPermits(int id, int permits, AtomicBoolean semaphore) {
            this.id = id;
            this.permits = permits;
            this.semaphore = semaphore;
        }

        public void release() {
            if(active.getAndSet(false)) {
                putPermits(semaphore, permits);
            }
        }

    }

    private void putPermits(AtomicInteger intSemaphore, int permits) {
        int c = intSemaphore.getAndAdd(permits);
        if(c <= 0) {

        }
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.
    }

    private DALPermitsSet destoryAcquire(BitSet ids, int overdraw) throws InterruptedException {
        DALPermitsSet permitsSet =
    }

    public String toString() {
        return "MulSemaphore [semaphore=" + Arrays.toString(semaphores) + "]";
    }

    public String getInfo(BitSet ids) {
        final StringBuilder sb = new StringBuilder();
        ids.stream().forEach(id -> {
            sb.append(id + ":");
            sb.append(semaphores[id].get());
            sb.append(",");
        });
        return "MulSemaphore [semaphore=" + sb.toString() + "]";
    }

    public DALPermitsSet newDalPermitsSet() {
        DALPermitsSet instance = dalPermitsSetPoll.poll();
        if(instance == null) {
            instance = new DALPermitsSet();

        }
    }
}
