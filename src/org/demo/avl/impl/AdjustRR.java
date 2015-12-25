package org.demo.avl.impl;

import org.demo.avl.interf.Adjustable;
import org.demo.avl.tree.AVLTree;
import org.demo.avl.tree.Node;

/**
 * <p>
 * 调节RR平衡的策略
 * </p>
 * 2015年12月24日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1.1
 */
public class AdjustRR implements Adjustable {

    @Override
    public void adjust(AVLTree tree, Node imbalanceNode) {
        Node parentNode = imbalanceNode.getParent();
        Node rightNode = imbalanceNode.getRight(); // 新添加的节点
        
        resetImbalanceNode(imbalanceNode, rightNode);
        resetRightNode(tree, rightNode, imbalanceNode, parentNode);
        resetParentNode(parentNode, rightNode);
    }
    
    /*
     * 重置失衡节点
     * 
     * @param imbalanceNode
     *      失衡节点
     */
    private void resetImbalanceNode(Node imbalanceNode, Node rightNode) {
        imbalanceNode.setRight(null);
        imbalanceNode.resetBF();
        imbalanceNode.resetHeight();
        imbalanceNode.setParent(rightNode);
    }
    
    /*
     * 重置失衡节点的左节点
     * 
     * @param rightNode
     *      右节点
     * @param imbalanceNode
     *      失衡节点
     * @param parentNode
     *      父节点
     */
    private void resetRightNode(AVLTree tree, Node rightNode, Node imbalanceNode, Node parentNode) {
        // 重置整棵树的根节点
        if (parentNode == null) {
            tree.resetRoot(rightNode);
        }
        
        rightNode.setParent(parentNode);
        rightNode.setLeft(imbalanceNode);
        rightNode.resetHeight();
        rightNode.resetBF();
    }
    
    /*
     * 重置父节点
     * 
     * @param parentNode
     *      父节点
     * @param rightNode
     *      右节点
     */
    private void resetParentNode(Node parentNode, Node rightNode) {
        if (parentNode == null || rightNode == null) {
            return;
        }
        
        if (rightNode.getValue() > parentNode.getValue()) {
            parentNode.setRight(rightNode);
        } else {
            parentNode.setLeft(rightNode);
        }
        
        parentNode.resetHeight();
    }
}
