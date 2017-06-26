package com.base.java.util.algrothim;

import java.util.Comparator;

public class RBTree<T extends Comparator<T>> {
    private RBTree<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

//    public class RBTNode<T extends Comparable<T>> {
//        boolean color;  //颜色
//        T key;          //关键字
//        RBTNode<T> left;    //做孩子
//        RBTNode right;
//        RBTNode parent;
//
//        public RBTNode(T key, boolean color; RBTNode<T> parent,RBTNode<T> left,
//                       RBTNode<T> right) {
//            this.key = key;
//            this.color = color;
//            this.parent = parent;
//            this.left = left;
//            this.right = right;
//        }
//
//    }
//
//    private void leftRotate(RBTNode<T> x) {
//        RBTNode<T> y =  x.right;
//        //将"y的左孩子,设为x的右孩子"
//        x.right = y.left;
//
//        if(y.left != null) {
//            y.left.parent = x;
//        }
//
//        //将x的父亲设为iy的父亲
//        y.parent = x.parent;
//
//        if(x.parent == null) {
//            this.root = y;
//        }
//
//    }
}
