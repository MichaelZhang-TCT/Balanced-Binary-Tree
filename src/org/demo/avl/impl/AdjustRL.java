package org.demo.avl.impl;

import org.demo.avl.interf.Adjustable;
import org.demo.avl.tree.AVLTree;
import org.demo.avl.tree.Node;

/**
 * <p>
 * 调节RL平衡的策略
 * </p>
 * 2015年12月24日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1
 */
public class AdjustRL implements Adjustable {

    @Override
    public void adjust(AVLTree tree, Node imbalanceNode) {
        Node rightChildNode = imbalanceNode.getRight();
        Node rightLeftNode = rightChildNode.getLeft();
        
        firstRightAdjust(tree, imbalanceNode, rightChildNode, rightLeftNode);
        secondLeftAdjust(tree, imbalanceNode, rightChildNode, rightLeftNode);
    }
    
    /*
     * 第一次右旋
     * (此处方法调用的顺序不要修改)
     * 
     * @param tree
     *      AVL树
     * @param imbalanceNode
     *      失衡节点
     * @param rightChildNode
     *      失衡节点的右孩子
     * @param rightLeftNode
     *      失衡节点右孩子的左孩子
     */
    private void firstRightAdjust(AVLTree tree, Node imbalanceNode, Node rightChildNode, Node rightLeftNode) {
        firstRightChildAdjust(rightChildNode, rightLeftNode);
        firstRightLeftAdjust(imbalanceNode, rightChildNode, rightLeftNode);
        firstImbalanceAdjust(imbalanceNode, rightLeftNode);
    }
    
    /*
     * 调整失衡节点
     * 
     * @param imbalanceNode
     *      失衡节点
     * @param rightLeftNode
     *      失衡节点右孩子的左孩子
     */
    private void firstImbalanceAdjust(Node imbalanceNode, Node rightLeftNode) {
        imbalanceNode.setRight(rightLeftNode);
        
        imbalanceNode.resetHeight();
        imbalanceNode.resetBF();
    }
    
    /*
     * 调整失衡节点的右孩子
     * 
     * @param rightChildNode
     *      失衡节点的右孩子
     * @param rightLeftNode
     *      失衡节点右孩子的左孩子
     */
    private void firstRightChildAdjust(Node rightChildNode, Node rightLeftNode) {
        rightChildNode.setParent(rightLeftNode);
        rightChildNode.setLeft(null);
        
        rightChildNode.resetHeight();
        rightChildNode.resetBF();
    }
    
    /*
     * 调整失衡节点右孩子的左孩子
     * 
     * @param imbalanceNode
     *      失衡节点
     * @param rightChildNode
     *      失衡节点的右孩子
     * @param rightLeftNode
     *      失衡节点右孩子的左孩子
     */
    private void firstRightLeftAdjust(Node imbalanceNode, Node rightChildNode, Node rightLeftNode) {
        rightLeftNode.setRight(rightChildNode);
        rightLeftNode.setParent(imbalanceNode);
        
        rightLeftNode.resetHeight();
        rightLeftNode.resetBF();
    }
    
    /*
     * 第二次左旋
     * 
     * @param tree
     *      AVL树
     * @param imbalanceNode
     *      失衡节点
     * @param rightChildNode
     *      失衡节点的右孩子
     * @param rightLeftNode
     *      失衡节点右孩子的左孩子
     */
    private void secondLeftAdjust(AVLTree tree, Node imbalanceNode, Node rightChildNode, Node rightLeftNode) {
        Node parentNode = imbalanceNode.getParent();
        
        secondRightChildAdjust(rightChildNode);
        secondImbalanceAdjust(imbalanceNode, rightLeftNode);
        secondRightLeftAdjust(tree, imbalanceNode, rightLeftNode, parentNode);
    }
    
    /*
     * 调整失衡节点
     * 
     * @param imbalanceNode
     *      失衡节点
     * @param rightLeftNode
     *      失衡节点右孩子的左孩子
     */
    private void secondImbalanceAdjust(Node imbalanceNode, Node rightLeftNode) {
        if (rightLeftNode.getLeft() != null) {
            imbalanceNode.setRight(rightLeftNode.getLeft());
        }
        
        imbalanceNode.setParent(rightLeftNode);
        
        imbalanceNode.resetHeight();
        imbalanceNode.resetBF();
    }
    
    /*
     * 调整失衡节点的右孩子
     * 
     * @param rightChildNode
     *      失衡节点的右孩子
     */
    private void secondRightChildAdjust(Node rightChildNode) {
        rightChildNode.resetHeight();
        rightChildNode.resetBF();
    }
    
    /*
     * 调整失衡节点右孩子的左孩子
     * 
     * @param tree
     *      AVL树
     * @param imbalanceNode
     *      失衡节点
     * @param rightLeftNode
     *      失衡节点右孩子的左孩子
     * @param parentNode
     *      失衡节点的父节点
     */
    private void secondRightLeftAdjust(AVLTree tree, Node imbalanceNode, Node rightLeftNode, Node parentNode) {
        rightLeftNode.setParent(parentNode);
        if (parentNode == null) {
            tree.resetRoot(rightLeftNode);
        }
        
        rightLeftNode.setLeft(imbalanceNode);
        
        rightLeftNode.resetHeight();
        rightLeftNode.resetBF();
    }
}
