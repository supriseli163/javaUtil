package com.base.java.util.algrothim;

/**
 * LRU算法:
 *  LRU是least Recently Used的缩写,即最近最少使用页面置换算法,
 *  是位虚拟页式存储管理服务的,是根据页面调用内存后的使用情况进行决策,
 *  由于无法预测页面将来的使用情况,只能利用"最近的过去"作为"最近的将来"的近似
 *  因此:LRU算法就是将最近最久未使用的页面予以淘汰
 */
public class LRU {
    /**队列的长度*/
    public static final int N = 5;

    /**存储的元素*/
    Object[] array = new Object[N];

    /**当前队列的大小*/
    public int size;

    public LRU() {

    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 队列是否已经满了
     * @return
     */
    public boolean isOutOfBoundary() {
        return size == N;
    }

    /**
     * 返回元素的位置,如果不存在,则返回-1
     * @param o
     * @return
     */
    public int indexOfElemet(Object o) {
        for(int i = 0; i < N; i++) {
            if(o == array[i]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 新添加数据
     * @param o
     * @return 移除的数据
     */
    public Object push(Object o) {
        int t = -1;
        if(!isOutOfBoundary() && indexOfElemet(o) == -1) {
            array[size] = o;
            size ++;
        } else if(isOutOfBoundary() && indexOfElemet(o) == -1) {
            for(int i = 0; i < size -1; i ++) {
                array[i] = array[i + 1];
            }
            array[size -1] = o;
        } else {
            t = indexOfElemet(o);
            for(int i = t; i < size - 1; i ++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = o;
        }

        if( -1 == t) {
            return null;
        } else {
            return array[t];
        }
    }


    public void showMemoryBlock() {
        for(int i = 0; i < size; i ++) {
            System.err.print(array[i] + ",");
        }
        System.err.print("\n");
    }

    public static void main(String[] args) {
        Integer inter[] = {4, 7, 0, 7, 1, 0, 1,2,6};

        LRU lru = new LRU();
        for(int i =0; i < inter.length; i ++) {
            lru.push(inter[i]);
            lru.showMemoryBlock();
        }
    }
}
