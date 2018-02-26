package com.base.java.util.algrothim.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BST binary search tree 二叉查找树
 * A definition of BST:
 *  1.The value in each node must be greater than (or equal to) any values stored in its left subtree.
 *   2.The value in each node must be less than (or equal to) any values stored in its right subtree.
 */
public class BST {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;
        TreeNode(int value) {
            this.value = value;
        }
    }

    public static boolean isValidBST(TreeNode treeNode) {
        if(treeNode == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                continue;
            }

            boolean leftCompareResult = false;
            boolean rightCompareResult = false;
            if(node.left != null) {
                leftCompareResult = (node.value >= node.left.value);
                queue.add(node.left);

            }
            if(node.right != null) {
                rightCompareResult = (node.value <= node.right.value);
                queue.add(node.right);
            }

            if(!(leftCompareResult & rightCompareResult)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);

    }
}
