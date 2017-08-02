package com.jet.dsm.binarytree;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/8/2 9:39
 * @Description:
 */


public class TreeNode<T> {

    private int index;
    private T data;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(int index, T data) {
        this.index = index;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
