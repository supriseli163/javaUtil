package com.base.java.util.algrothim;

import java.util.BitSet;

/**
 * BitSet 使用场景,整数,无重复
 *
 * BitSet:也就是位图, 由于可以用非常紧凑的格式来表示给定范围连续数据而经常出现各种算法设计中
 *
 * 基本原理是: 用1位来表示一个数据是否出现过,0表示没有出现过,1表示出现过.
 *            使用的时候既可以根据某一个是否为0表示次数是否出现过.
 * 因此:
 *  1G的空间可以表示: 8*1024*1024*1024 = 8.58*10^9 bit ,也就是说可以表示85亿个数
 *
 *  常见的应用是那些需要对海量数据进行一些统计工作的时候,比如日志分析,大量的数据排序等.
 *
 *
 */
public class BitSetData {
    public static void main(String[] args) {
        BitSet bits1 = new BitSet();
        BitSet bits2 = new BitSet(16);

        bits1.set(1);
        bits1.set(2);
        System.err.println(bits1.size());
        for(int index = 0; index <= bits1.size(); index++) {
            System.err.print(bits1.get(index) + " ");
        }


        sortArray();
    }

    /**
     * 位图法排序
     */
    public static void sortArray() {
        int [] array = new int[]{423, 700, 9999, 2323, 356, 6400, 1, 2,3,2};
        BitSet bitSet = new BitSet(2 << 13);
        //虽然可以自动扩容,但尽量在构造时指定估算大小,默认为64
        System.err.println("BitSet size: " + bitSet.size());

        for(int i =0 ; i < array.length; i++) {
            bitSet.set(array[i]);
        }

        //删除重复数字后的元素个数
        int bitLen = bitSet.cardinality();

        int[] orderedArray = new int[bitLen];
        int k = 0;
        for(int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            orderedArray[k++] = i;
        }
        System.err.println("After ordering: ");

        for(int i = 0; i < bitLen; i ++) {
            System.err.println(orderedArray[i] + "\t");
        }
    }
}
