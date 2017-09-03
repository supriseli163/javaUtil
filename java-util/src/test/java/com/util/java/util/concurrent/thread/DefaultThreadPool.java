package com.util.java.util.concurrent.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    private List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private int workNum = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(workNum);
    }

    public void execute(Job job) {
        if(job != null) {
            /**把job添加到工作队列中,然后等待通知*/
            jobs.addLast(job);
            /**添加一个job后,对工作队列jobs调用了其notify()方法,而不是notifyAll()方法,因为能够确定有工作者线程
             * 被唤醒,这时使用notify()方法将会比notifyAll()方法获得更小的开销
             * */
            jobs.notify();
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs) {
            /**限制新增的Worker数量*/
            if(num + this.workNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workNum;
            }
        }
    }

    public void removeWorker(int num) {
        synchronized (jobs) {
            if(num > this.workNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if(workers.remove(worker)) {
                    worker.shutdown();
                    count ++;
                }
            }
            this.workNum -= count;
        }
    }

    public void shutdown() {
        for(Worker worker : workers) {
            worker.shutdown();
        }
    }

    public int getJosSize() {
        return jobs.size();
    }

    private void initializeWorkers(int workNum) {
        for(int i = 0; i < workNum; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "Thread-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    class Worker implements Runnable {
        //是否工作
        private volatile boolean running = true;
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            /**感知到外部对WorkerThread的中断操作,返回*/
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if(job != null) {
                    try {
                        job.run();
                    } catch (Exception ex) {
                        //忽略Job执行中的Exception
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
