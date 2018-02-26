package com.base.java.util.algrothim.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class TreeAlgorithm {
    public static String treeNodeToString(TreeNode root) {
        if(root == null) {
            return null;
        }

        String outPut = "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                outPut += "null,";
                continue;
            }
            outPut += node.value + ",";
            queue.add(node.left);
            queue.add(node.right);
        }
        return "[" + outPut.substring(0, outPut.length() - 2) + "]";
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }



    public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.value);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }


    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node,  "", true);
    }

    /**
     * BST binary search tree 二叉查找树
     * A definition of BST:
     *  1.The value in each node must be greater than (or equal to) any values stored in its left subtree.
     *   2.The value in each node must be less than (or equal to) any values stored in its right subtree.
     */
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

    public static class  TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            value = x;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            boolean isBST = isValidBST(root);
            System.err.println(String.format("TreeNode:[%s],isBST:[%s]", root, isBST));
            prettyPrintTree(root);
        }
    }
}
