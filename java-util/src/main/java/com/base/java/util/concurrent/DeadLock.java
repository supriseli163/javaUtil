package com.base.java.util.concurrent;

public class DeadLock {
    /**
     * A锁
     */
    private final String A = "A";
    private final String B = "B";

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    /**
     * 避免死锁的方法:
     * 1,避免一个线程同时获取多个线程
     * 2,避免一个线程在锁内同时占用多个资源,
     * 尽量保证每个锁占用一个资源
     * 3,尝试使用定时锁,使用tryLock(timeout)来替代使用内部锁机制
     * 4,对于数据库锁,加锁和解锁必须在一个数据库连接里,否则会出现解锁失败
     */
    public void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.err.print("1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.err.println("2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

    /**
     * 2017-05-09 23:47:31
     Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.73-b02 mixed mode):

     "Attach Listener" #13 daemon prio=9 os_prio=31 tid=0x00007fe45d866800 nid=0x320b waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "DestroyJavaVM" #12 prio=5 os_prio=31 tid=0x00007fe45c808800 nid=0x1703 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Thread-1" #11 prio=5 os_prio=31 tid=0x00007fe45c915000 nid=0x5103 waiting for monitor entry [0x000070000134f000]
     java.lang.Thread.State: BLOCKED (on object monitor)
     at com.base.java.util.concurrent.DeadLock$2.run(DeadLock.java:36)
     - waiting to lock <0x0000000795872030> (a java.lang.String)
     - locked <0x0000000795872060> (a java.lang.String)
     at java.lang.Thread.run(Thread.java:745)

     "Thread-0" #10 prio=5 os_prio=31 tid=0x00007fe45d865800 nid=0x4f03 waiting for monitor entry [0x000070000124c000]
     java.lang.Thread.State: BLOCKED (on object monitor)
     at com.base.java.util.concurrent.DeadLock$1.run(DeadLock.java:25)
     - waiting to lock <0x0000000795872060> (a java.lang.String)
     - locked <0x0000000795872030> (a java.lang.String)
     at java.lang.Thread.run(Thread.java:745)

     "Monitor Ctrl-Break" #9 daemon prio=5 os_prio=31 tid=0x00007fe45d0b2000 nid=0x4d03 runnable [0x0000700001149000]
     java.lang.Thread.State: RUNNABLE
     at java.net.PlainSocketImpl.socketAccept(Native Method)
     at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
     at java.net.ServerSocket.implAccept(ServerSocket.java:545)
     at java.net.ServerSocket.accept(ServerSocket.java:513)
     at com.intellij.rt.execution.application.AppMain$1.run(AppMain.java:90)
     at java.lang.Thread.run(Thread.java:745)

     "Service Thread" #8 daemon prio=9 os_prio=31 tid=0x00007fe45d065000 nid=0x4903 runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C1 CompilerThread2" #7 daemon prio=9 os_prio=31 tid=0x00007fe45d00d000 nid=0x4703 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C2 CompilerThread1" #6 daemon prio=9 os_prio=31 tid=0x00007fe45d812000 nid=0x4503 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C2 CompilerThread0" #5 daemon prio=9 os_prio=31 tid=0x00007fe45d00c000 nid=0x4303 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007fe45d00b800 nid=0x360f runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007fe45c021000 nid=0x3003 in Object.wait() [0x000070000092e000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x0000000795587110> (a java.lang.ref.ReferenceQueue$Lock)
     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
     - locked <0x0000000795587110> (a java.lang.ref.ReferenceQueue$Lock)
     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
     at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

     "Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007fe45d024000 nid=0x2e03 in Object.wait() [0x000070000082b000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x0000000795586b50> (a java.lang.ref.Reference$Lock)
     at java.lang.Object.wait(Object.java:502)
     at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:157)
     - locked <0x0000000795586b50> (a java.lang.ref.Reference$Lock)

     "VM Thread" os_prio=31 tid=0x00007fe45d80c000 nid=0x2c03 runnable

     "GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007fe45c819000 nid=0x2403 runnable

     "GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007fe45d80a000 nid=0x2603 runnable

     "GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007fe45d80b000 nid=0x2803 runnable

     "GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007fe45d80b800 nid=0x2a03 runnable

     "VM Periodic Task Thread" os_prio=31 tid=0x00007fe45c028000 nid=0x4b03 waiting on condition

     JNI global references: 21


     Found one Java-level deadlock:
     =============================
     "Thread-1":
     waiting to lock monitor 0x00007fe45d029f58 (object 0x0000000795872030, a java.lang.String),
     which is held by "Thread-0"
     "Thread-0":
     waiting to lock monitor 0x00007fe45d0274b8 (object 0x0000000795872060, a java.lang.String),
     which is held by "Thread-1"

     Java stack information for the threads listed above:
     ===================================================
     "Thread-1":
     at com.base.java.util.concurrent.DeadLock$2.run(DeadLock.java:36)
     - waiting to lock <0x0000000795872030> (a java.lang.String)
     - locked <0x0000000795872060> (a java.lang.String)
     at java.lang.Thread.run(Thread.java:745)
     "Thread-0":
     at com.base.java.util.concurrent.DeadLock$1.run(DeadLock.java:25)
     - waiting to lock <0x0000000795872060> (a java.lang.String)
     - locked <0x0000000795872030> (a java.lang.String)
     at java.lang.Thread.run(Thread.java:745)

     Found 1 deadlock.
     */
}
