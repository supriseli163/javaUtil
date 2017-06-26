package com.base.java.util.algrothim;

public class RedBlackTree {
    /**
     * RB-Tree 红黑树,一个特殊的平衡二叉树
     * RB-Tree的好处在于检索很快,主要用于关联式容器
     *
     * Set map multimap的底层实现都是RB-Tree,这些关联容器存入的时候都是自动排序
     * 查询的时候很快,不过代价就是RB-Tree树的插入和删除带来的麻烦很多
     * 不过还好的是,可以是它可以在o(log(n))时间内查找,插入或者删除
     *
     *
     * 性质1:每个节点只能是黑色或者红色
     * 性质2:根节点是黑色
     * 性质3:所有叶子节点都是黑色(叶子是NULL节点)
     * 性质4:每个红色的两个孩子节点都是黑色
     * 性质5:从任一节点到其每个叶子的所有简单路径都包含相同数目的
     *      黑色节点.通过对任何一条从跟到叶子的路径上各个节点着色方式的限制
     *      红黑树确保没有一条路径会比其他路径长出两倍,因而是接近平衡的.
     *
     *
     *
     *红黑树的应用:
     *  红黑树的应用比较广泛,主要用来存数数据,他的时间复杂度是o(lgn)
     *  效率非常高,java集合中的treeset和treeMap, C++, STL中的set, map
     *  以及Linux的虚拟内存管理,用的都是红黑树实现的.
     *
     *
     *
     *
     * RB-Tree Node:
     *         (1) color
     *         (2) key
     *         (3) left
     *         (4) right
     *         (5) p
     *
     */
//    private const boolean RED = true;
//    private private static final int  = 765;
//
//    class Node {
//        public Node left;
//        public Node right;
//        public
//    }
}
