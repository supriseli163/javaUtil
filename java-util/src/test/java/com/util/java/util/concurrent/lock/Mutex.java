package com.util.java.util.concurrent.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Mutex是一个不可重入的互斥锁实现
 * 锁资源(ASQ里的state)
 * 只有两种状态:0 表示未锁定    1表示锁定
 *
 *
 * tryAcquire(int) 独占方式,尝试获取资源,成功则返回true,失败则返回false
 * tryRelease(int) 独占方式,尝试释放资源,成功则返回true,失败则返回false
 * tryAcquireShared(int) 共享方式,尝试获取资源,负数表示失败,0表示成功,但没有剩余可用资源,正数表示成功,且有剩余资源
 * tryReleaseShared(int)：共享方式。尝试释放资源，成功则返回true，失败则返回false。
 */
public class Mutex implements Lock, Serializable {
    private static class Sync extends AbstractQueuedSynchronizer {
        private boolean isHeldExeclusively() {
            return getState() == 1;
        }

        /**尝试获取资源,立即返回,成功则返回true,否则返回false*/
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if(compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int releases) {
            assert releases == 1;
            /**既然是释放,*/
            if(getState() == 0) {

            }
            return false;
        }
    }

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
