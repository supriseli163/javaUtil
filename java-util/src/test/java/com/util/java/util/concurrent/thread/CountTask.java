package com.util.java.util.concurrent.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *  @author supriseli
 *  @version 1.1.1
 *
 * Fork Join框架:
 *  Fork/Join框架是java 7 提供的一个用于并行执行任务的框架,是一个把大人物分割成若干个小任务,
 *  最终汇总每个小任务结果后得到大任务结果的框架
 *   Fork就是把一个大任务切分成若干个子任务的执行,Join就是合并这些子任务的执行结果,最后得到这个大任务的结果
 *   比如:计算1+2+3...+10000
 *   可以分割成10个子任务,每个子任务分别对1000个数进行求和,最终汇总这10个子任务的结果.
 *
 * Fork/Join框架的设计
 *  步骤1 分割任务 首先我们需要有一个fork类来把大任务分割成子任务,有可能子任务还是很大,所以还需要不停地分割,知道分割出的子任务足够小
 *  步骤2 执行任务合并结果 分割的子任务分别放在双端队列里然后几个启动线程分别从双端队列里获取任务并执行,子任务执行完成的结果
 *      统一放在一个队列里,启动一个线程从队列里拿数据,然后合并这些数据
 *
 *      Fork/Join使用这两个类来完成以上两件事情
 *          1.ForkJoinTask 我们要使用ForkJoin框架,必须首先创建一个ForkJoin任务,它提供在任务执行fork()和join框架的机制
 *          通常情况下,我们不需要直接集成ForkJoinTask类,只需要集成它的子类,Fork()/Join()框架提供了以上两个子类
 *          RecursiveAction 用于没有返回结果的任务
 *          RecursiveTask   用于有返回结果的任务
 *          ForkJoinPool    ForkJoinPool需要通过ForkJoinPool来执行
 *
 *          任务分割出的子任务会添加到当前工作线程所维护的双端队列中,进入队列的头部
 *          当前一个工作线程的队列暂时没有任务时,它会随机从其他工作线程的队列的尾部获取一个任务
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2; //阈值
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for(int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务大于阈值,就分裂成两个任务的计算
            int middle = (start + end)/2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行完,并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
      long startTime = System.currentTimeMillis();
        testForkJoin();
        System.err.println("Use Fork/Join cost time:" + (System.currentTimeMillis() - startTime));
        testAutoCompute();
        System.err.println("Use Auto Compute cost time:" + (System.currentTimeMillis() - startTime));
    }

    public static void testForkJoin() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务,负责计算1+2+3+4
        CountTask task = new CountTask(1,100000000);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException ex) {

        }catch (ExecutionException ex) {

        }
    }

    public static void  testAutoCompute() {
        int start = 1;
        int end = 100000000;
        int result = 0;
        for(int index = start; index <= end; index ++) {
            result += index;
        }
        System.err.println(result);
    }
}
