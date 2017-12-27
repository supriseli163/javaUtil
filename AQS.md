**AQS**
##  AQS简介
AQS (java.util.concurrent.locks.AbstractQueuedSynchronizer),是java.util.concurrent包的核心类,是用来构建锁或其他同步组件的基础,它使用了一个int成员变量表示同步状态,通过内置的FIFO队列来完成资源获取线程的工作,
同步器
包括Lock,ReentrantLock,Semaphore,CountDownLatch, ReadWriteLock都依赖于AQS的设计思想,所以说
AQS是阅读整个并发包源码的一个突破口.

## 同步器与锁的关系
    同步器是实现锁的关键,在锁的实现中聚合同步器,利用同步器实现锁的语义,可以这样理解二者之间的关系:锁是面向使用者的,它定义了使用者与锁交互的接口,隐藏了实现细节;
    同步器是面向锁的实现者,它简化了锁的实现方式,屏蔽了同步状态的管理,线程的排队,等待与唤醒等底层操作,锁和

