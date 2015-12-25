package org.demo.avl.tree;

public class Node {

    private int value;
    private Node left;
    private Node right;
    private int height; // 当前节点的高度
    private Node parent;
    private int BF; // 平衡因子

    public Node(int _value) {
        this.value = _value;
    }
        
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    /**
     * 当前节点是否有两个子节点
     * 
     * @return
     *      结果
     */
    public boolean isTwoChildren() {
        return (left != null && right != null);
    }
    
    public void setBF(int _BF) {
        BF = _BF;
    }
    
    public int getBF() {
        return BF;
    }
    
    @Override
    public String toString() {
        return "[" + String.valueOf(value) + ", " + getHeight() + "]";
    }
}
