package com.util.java.util;

import java.util.Arrays;

/**
 * DFS: deep first
 *
 *
 *
 * BFS(广度优先搜索):
 *  从某个顶点出发,首先访问这个顶点,然后找出这个节点所有未被访问的的邻接点,
 *  访问完后再访问这些节点中第一个邻接点的所有接点,重复此方法,直到所有接点都被访问为止
 *
 *  应用:树的层序遍历
 *  网络爬虫,寻找最短路径等.
 *
 *  DAG: (Directed asyclic graph)
 *  如果有一个非有向无环图,且A点出发向B经C,可回到A,形成一个环,将从C到A的边方向改为从A到C,
 *  则变成有向无环图,有向无环图的生成树个数等于入度非零的节点的入度积.
 *  DAG:在图论中,如果一个有向图无法从某个顶点出发经过若干条边回到该店,则这个图是一个有向无环图(DAG图)
 *  因为一个有向图种一个点经过两种路线到达另一个点未必形成环,因此有向图未必能转化成树,但任何
 *  有向树均为有向无环图.
 */
public class DFSTest {
    /**
     * DFS deep first search
     */
    public static void dfs() {

    }

    public static void brs() {

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int [] merged = new int[m + n];
        //merged = Arrays.copyOfRange(nums1, 0, m - 1);
        for(int index = 0; index < m; index ++) {
            merged[index] = nums1[index];
        }

        for(int index = 0; index < n; index ++) {
            merged[m + index] = nums2[index];
        }


        boolean sequence;
        if(m == 1) {
            sequence = true;
        } else {
            sequence = nums1[1] > nums1[0];
        }

        for(int i = 0; i < m + n - 1; i ++) {
            for(int j = i + 1; j < m + n; j ++) {
                if(sequence) {
                    if(merged[i] > merged[j]) {
                        int internal = merged[i];
                        merged[j] = merged[i];
                        merged[i] = internal;
                    }
                }else {
                    if(merged[i] < merged[j]) {
                        int internal = merged[i];
                        merged[i] = merged[j];
                        merged[j] = internal;
                    }
                }
            }
        }

        System.err.print(Arrays.toString(merged));
    }

    public static void main(String[] args) {
        int [] a = {1,2,3,4};
        int [] b = {5,6,7,8};
        merge(a, 4, b, 4);
    }
}
