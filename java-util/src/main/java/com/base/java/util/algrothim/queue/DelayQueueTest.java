package com.base.java.util.algrothim.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue:
 *  Delayed元素的一个无界阻塞队列,只有在延迟期满时才能从中取出元素,
 *  该队列的头部是延迟期满后保存时间最长的Delayed元素,如果延迟都还没有期满,则队列没有头部,
 *
 *  并且poll将返回null, 当一个元素的getDelay(TimeUnit.NANOSECONDS)方法返回一个小于等于0的值时,
 *  将发生到期,即无法使用take或poll移除未到期的元素,也不会将这些原色正常对待.
 *
 *
 */
public class DelayQueueTest {
    /**
     * ex:夏天买来一批食品放入冷藏室,每种食品在冷藏都有一个保存时间,超过该时间就会变质,食品检查员经常检查食品,
     * 超过冷藏时间的食品就要拿出来扔掉
     */
    class Food implements Delayed {
        private String foodName;
        private long saveTime;
        private long expireTime;

        public Food(String foodName, long saveTime) {
            this.foodName = foodName;
            this.saveTime = saveTime;
            this.expireTime = TimeUnit.NANOSECONDS.convert(saveTime, TimeUnit.SECONDS) + System.nanoTime();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expireTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Food that = (Food)o;
            if(this.expireTime > that.expireTime) {
                return 1;
           } else if(this.expireTime == that.expireTime) {
                return 0;
            } else {
                return -1;
            }
        }

        public String getFoodName() {
            return this.foodName;
        }

        public long getSaveTime() {
            return this.saveTime;
        }

        public long getExpireTime() {
            return this.expireTime;
        }
    }

    class FoodChecker implements Runnable {
        private DelayQueue<Food> queue;

        public FoodChecker(DelayQueue<Food> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("开始检查!");
                boolean flag = true;
                while (flag) {
                    Food food = queue.take();
                    System.err.println(food.getFoodName() + "食品过期!保存时间:" + food.getSaveTime() + "天.");
                    if(queue.isEmpty()) {
                        flag = false;
                    }
                }
                System.err.println("检查完毕!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DelayQueue<Food> queue = new DelayQueue<Food>();

        Random r = new Random();
        //queue.add(new Food("A", getRan))
    }

    public static void getRandomDay() {

    }
}
