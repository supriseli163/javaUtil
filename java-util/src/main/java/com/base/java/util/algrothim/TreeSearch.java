package com.base.java.util.algrothim;

public class TreeSearch {
    /**
     * 二叉查找树:
     *  又叫二叉排序树
     *  二叉查找树或者是一颗空树,或者是一颗具有如下性质的二叉树
     *  对于任何一个节点X:
     *      若它的左子树非空,则左子树上所有节点均值小于等于X的值
     *      若它的右子树非空,则右子树上所有节点的均值大于等于X的值
     *  按中序遍历二叉查找树,所得到的中序遍历序列是一个递增(或递减)的有序序列
     *
     *
     *  查找:
     *      我们先来看一下查找:
     *          给定一个树的树根节点指针X和关键字K,返回指向指针关键字的指针,否则返回NUll
     *
     *
     * Tree-search(x,k)
     *  Tree-search(x, k)
     *      if x = NIL or k = key[x]:
     *          then return x
     *      if k < key[x]
     *          then return tree-search(left[x], k)
     *          else
     *              return tree-search(right[x], k)
     *
     * 在递归查找的过程中遇到的节点即构成了一条由树根下降的路径
     * 故:tree-search的时间复杂度是0(H).
     *
     *
     * 搜索二叉查找树的最大值和最小值:
     *      那么如何去找二叉查找树的最大值和最小值?
     * 最大值是二叉树的最右结点:
     *      从根节点开始沿着right指针一路走到right指针为空
     * 最小值是在最左节点:
     *      从根节点沿着left指针一路走到left指针为空
     *
     * TREE-MINIMUM(x)
     *      while left[x] != NULL
     *          do x <---- left[x]
     *      return x
     * 时间复杂度:o(H)
     *
     * TREE-MAXIMUM(x)
     *      while right[x] != NULL
     *          do x <---- right[x]
     *      return x
     * 时间复杂度o(H),寻找的过程就是根节点一路下降的过程
     *
     *
     * 设置前驱和后继:
     *      1,对应后继,对于一个节点来说,后继无非是有3种情况:
     *          1,x有右孩子,那么x的后继就是右孩子的最左节点
     *          2,x无右孩子,而且x是其父母的左还在,那么x后继就是其父母节点(可能是NIL)
     *          3,x无右孩子,而且x是其父母的右孩子,那么x的后继就是x的某个祖先(这个最低祖先的
     *          左孩子也是x的祖先,此时让父母成为新的x,寻找不断查找x是其父母的左孩子的节点
     *          如果此时出现一个x节点是其父母左孩子,那个父母就是x的后继,这个意思也就是说第三种情况
     *          变成第二种情况,如果这个时候找不到则得到的就是NIL的节点)
     *
     *
     * TREE-INSERT的时间复杂富是o(H)
     *
     *
     *
     * 删除
     *
     */
}
