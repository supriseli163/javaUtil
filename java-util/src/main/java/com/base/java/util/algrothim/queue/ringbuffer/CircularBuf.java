package com.base.java.util.algrothim.queue.ringbuffer;

public class CircularBuf {
    int MAX = 3;
    int iput = 0; /**环形缓冲区的当前放入位置*/
    int iget = 0; /**缓冲区的当前取出位置*/
    int n = 0;    /**缓冲区中的元素总量*/

    double buffer[] = new double[MAX];

    public int addRing(int i) {
        return (i + 1) == MAX ? 0 : i +1;
    }

    /**从环形缓冲区取出一个元素*/
    public double get() {
        int pos;
        if(n > 0) {
            pos = iget;
            iget = addRing(iget);
            n --;
            System.out.println("get-->" + buffer[pos]);
            return buffer[pos];
        } else {
            System.out.println("Buffer is Empty");
            return 0.0;
        }
    }

    /**向环形缓冲区中放入一个元素*/
    public void put(double z) {
        if(n < MAX) {
            buffer[iput] = z;
            System.out.print("put<---" + buffer[iput]);
            iput = addRing(iput);
            n ++;
        } else {
            System.out.println("Buffer is full");
        }
    }

    public static void main(String[] args) {
        CircularBuf cb = new CircularBuf();
        cb.put(1);
        cb.put(2);
        cb.put(3);
        cb.put(1);
        cb.put(2);
        cb.put(3);
        cb.get();
        cb.put(4);
        cb.get();
        cb.get();
        cb.get();
        cb.get();
        cb.get();
        cb.get();
        cb.get();
        cb.get();
        cb.get();
    }
}
