package com.github.zack.use.java.base.collection.structure.avl;

/**
 * @author zack
 * @since 2024/12/11
 */
public class AVLTree {

    private AVLTreeNode root;

    // 获取节点高度
    private int height(AVLTreeNode node) {
        return node == null ? 0 : node.height;
    }

    // 计算平衡因子
    private int getBalance(AVLTreeNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 右旋操作
    private AVLTreeNode rightRotate(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T = x.right;

        x.right = y;
        y.left = T;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋操作
    private AVLTreeNode leftRotate(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T = y.left;

        y.left = x;
        x.right = T;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入节点
    public AVLTreeNode insert(AVLTreeNode node, int key) {
        if (node == null) return new AVLTreeNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // 不允许重复键

        // 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 获取平衡因子
        int balance = getBalance(node);

        // 平衡操作
        if (balance > 1 && key < node.left.key) return rightRotate(node); // LL
        if (balance < -1 && key > node.right.key) return leftRotate(node); // RR
        if (balance > 1 && key > node.left.key) { // LR
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) { // RL
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void inOrder(AVLTreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public void printTree() {
        inOrder(root);
        System.out.println();
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.printTree(); // 输出：10 20 30 40 50
    }

}
