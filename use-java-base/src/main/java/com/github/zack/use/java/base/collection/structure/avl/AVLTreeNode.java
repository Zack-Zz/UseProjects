package com.github.zack.use.java.base.collection.structure.avl;

/**
 * @author zack
 * @since 2024/12/11
 */
public class AVLTreeNode {

    int key, height;
    AVLTreeNode left, right;

    AVLTreeNode(int key) {
        this.key = key;
        this.height = 1; // 新节点高度为 1
    }

}
