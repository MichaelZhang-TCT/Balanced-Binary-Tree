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
    
    /**
     * 重置节点高度
     */
    public void resetHeight() {
        int leftHeight = 0;
        int rightHeight = 0;
        Node leftNode = getLeft();
        Node rightNode = getRight();
        
        leftHeight = leftNode == null ? -1 : leftNode.getHeight();
        rightHeight = rightNode == null ? -1 : rightNode.getHeight();
        
        setHeight(Math.max(leftHeight, rightHeight) + 1);
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
    
    /**
     * 是不是叶子节点
     * 
     * @return
     *      结果
     */
    public boolean isLeaf() {
        return (left == null && right == null);
    }
    
    public void setBF(int _BF) {
        BF = _BF;
    }
    
    public int getBF() {
        return BF;
    }
    
    /**
     * 重置节点BF
     */
    public void resetBF() {
        int leftHeight = 0;
        int rightHeight = 0;
        Node leftNode = getLeft();
        Node rightNode = getRight();
        
        leftHeight = leftNode == null ? 0 : leftNode.getHeight() + 1;
        rightHeight = rightNode == null ? 0 : rightNode.getHeight() + 1;
        
        setBF(leftHeight - rightHeight);
    }
    
    @Override
    public String toString() {
        return "[" + String.valueOf(value) + ", " + getBF() + "]";
    }
}
