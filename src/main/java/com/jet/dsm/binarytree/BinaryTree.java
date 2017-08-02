package com.jet.dsm.binarytree;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/8/2 9:34
 * @Description:二叉树实现
 */


public class BinaryTree {

    private TreeNode root;


    public BinaryTree() {
        root = new TreeNode(1, "A");

    }

    public TreeNode create() {
        root.setLeftNode(new TreeNode(2, "B"));
        root.setRightNode(new TreeNode(2, "C"));
        root.getLeftNode().setLeftNode(new TreeNode(3, "D"));
        root.getLeftNode().setRightNode(new TreeNode(3, "E"));
        root.getRightNode().setLeftNode(new TreeNode(4, "F"));
        root.getRightNode().setRightNode(new TreeNode(4, "G"));
        return root;
    }

    public int getHeight(TreeNode t) {
        if (t == null) {
            return 0;
        }
        int leftHeight = getHeight(t.getLeftNode());
        int rightHeight = getHeight(t.getRightNode());
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    public int getSize(TreeNode t){
        if(t == null){
            return 0;
        }
        int leftSize = getSize(t.getLeftNode());
        int rightSize = getSize(t.getRightNode());
        return leftSize + rightSize + 1;
    }

    //先序
    public void beforeOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.getData());
        beforeOrder(root.getLeftNode());
        beforeOrder(root.getRightNode());
    }

    //中序
    public void middleOrder(TreeNode root){
        if(root == null){
            return;
        }
        middleOrder(root.getLeftNode());
        System.out.print(root.getData());
        middleOrder(root.getRightNode());
    }

    //后序
    public void afterOrder(TreeNode root){
        if(root == null){
            return;
        }
        afterOrder(root.getLeftNode());
        afterOrder(root.getRightNode());
        System.out.print(root.getData());
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.create();
        System.out.println("二叉树高度："+binaryTree.getHeight(binaryTree.getRoot()));
        System.out.println("二叉树大小："+binaryTree.getSize(binaryTree.getRoot()));
        binaryTree.beforeOrder(binaryTree.getRoot());
        System.out.println("----");
        binaryTree.middleOrder(binaryTree.getRoot());
        System.out.println("----");
        binaryTree.afterOrder(binaryTree.getRoot());
    }
}
