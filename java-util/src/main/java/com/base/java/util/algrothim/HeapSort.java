package com.base.java.util.algrothim;

public class HeapSort {
    /**
     * HeapSort:
     *  堆的定义
     *      n个关键字序列k1, k2,,,,kn称为堆,当且仅当该序列满足如下性质(简称为堆性质)
     *      (1) k(i) < k(2i) 且k(i) < k(2i+ 1)  (1<= i <= n) 小根堆
     *      (2) k(i) > k(2i) 且 k(i) > k(2i+1) (1 <= i <= n) 大根堆
     * 若将此序列锁存储的向量R[1...n]看做是一颗完全二叉树的存储结构,则堆实质上是满足
     * 如下性质的完全二叉树:
     *  树种任一非叶节点的关键字均不大于(或不小于)其左右孩子(若存在)节点的关键字
     *
     *
     *  堆排序算法的特性:
     *      堆排序时间,主要由建立初始堆和反复堆这两部分的时间开销构成,他们均是通过调用heapify实现的.
     *      由于建初始堆所需的比较次数较多,所以堆排序不适宜记录次数较少的文件.
     *      堆排序是就地排序,辅助空间为O(1)
     *      它是不稳定的排序方法
     *
     *  堆排序的过程:
     *      (1),最大堆调整(max_heapify):将堆的末端子节点做调整,使得子节点永远小于父节点
     *      (2),创建最大堆(build_max_heap):将堆所有数据重新排序
     *      (3),排序(Heap_sort):移除位在第一个数据的根节点,并做最大堆调整的递归运算
     */

    /**
     * 堆排序:时间复杂度 O(N-1 * log(N))
     * logN 为构造堆的时间
     * @param data
     *
     * http://dsbryz.iteye.com/blog/1182056
     */
    public static void sort(int[] data) {
        //构建最大堆
        buildMaxHeap(data);
        //循环,每次把根节点和最后一个节点调换位置
        for(int i = data.length; i > 1; i --) {
            int tmp = data[0];
            data[0] = data[i -1];
            data[i -1] = tmp;

            //堆的长度减1,排除置换到最后未知的根节点
            maxHeapify(data, 1, i - 1);
        }
    }

    private static void buildMaxHeap(int[] data) {
        for(int i = data.length / 2; i > 0; i--) {
            maxHeapify(data, i, data.length);
        }
    }

    /**
     * 调整堆,使其生成最大堆
     *
     * @param data
     * @param parentNodeIndex
     * @param headSize
     */
    private static void maxHeapify(int[] data, int parentNodeIndex, int headSize) {
        //左子节点索引
        int leftChildNodeIndex = parentNodeIndex * 2;
        //右子节点索引
        int rightChildNodeIndex = parentNodeIndex * 2 + 1;
        //最大节点索引
        int largestNodeIndex = parentNodeIndex;

        /**
         * 如果左节点大于父节点,则将左节点作为最大节点
         */
        if(leftChildNodeIndex <= headSize && data[leftChildNodeIndex -1] > (data[parentNodeIndex - 1])) {
            largestNodeIndex = leftChildNodeIndex;
        }

        /**
         * 如果右节点比最大节点还大,那么最大节点应该是右子节点
         */
        if(rightChildNodeIndex <= headSize && data[rightChildNodeIndex -1] > (data[largestNodeIndex -1])) {
            largestNodeIndex = rightChildNodeIndex;
        }

        //最后,如果最大节点和父节点不一致,则交换他们的值
        if(largestNodeIndex != parentNodeIndex) {
            int tmp = data[parentNodeIndex - 1];
            data[parentNodeIndex -1] = data[largestNodeIndex -1];
            data[largestNodeIndex - 1] = tmp;

            //交换完父节点和子节点的值,对换了值的子节点检查是否符合最大堆的特性
            maxHeapify(data, largestNodeIndex, headSize);
        }
    }

    public static void main(String[] args) {
        int a[] = new int[] {1, 10, 4 ,5,6, 100, 2, 4, 5, 6, 9999};
        long start = System.currentTimeMillis();
        sort(a);
        System.err.println("all times is:" + (System.currentTimeMillis() - start));
        for(int i =0; i< a.length; i++) {
            System.err.println(a[i]);
        }
    }
}
