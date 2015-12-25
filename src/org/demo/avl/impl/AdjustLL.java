package org.demo.avl.impl;

import org.demo.avl.interf.Adjustable;
import org.demo.avl.tree.AVLTree;
import org.demo.avl.tree.Node;

/**
 * <p>
 * 调节LL平衡的策略
 * </p>
 * 2015年12月24日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1.1
 */
public class AdjustLL implements Adjustable {

    @Override
    public void adjust(AVLTree tree, Node imbalanceNode) {
        Node parentNode = imbalanceNode.getParent();
        Node leftNode = imbalanceNode.getLeft(); // 新添加的节点
        
        resetImbalanceNode(imbalanceNode, leftNode);
        resetLeftNode(tree, leftNode, imbalanceNode, parentNode);
        resetParentNode(parentNode, leftNode);
    }

    /*
     * 重置失衡节点
     * 
     * @param imbalanceNode
     *      失衡节点
     */
    private void resetImbalanceNode(Node imbalanceNode, Node leftNode) {
        imbalanceNode.setLeft(null);
        imbalanceNode.resetBF();
        imbalanceNode.resetHeight();
        imbalanceNode.setParent(leftNode);
    }
    
    /*
     * 重置失衡节点的左节点
     * 
     * @param leftNode
     *      左节点
     * @param imbalanceNode
     *      失衡节点
     * @param parentNode
     *      父节点
     */
    private void resetLeftNode(AVLTree tree, Node leftNode, Node imbalanceNode, Node parentNode) {
        // 重置整棵树的根节点
        if (parentNode == null) {
            tree.resetRoot(leftNode);
        }
        
        leftNode.setParent(parentNode);
        leftNode.setRight(imbalanceNode);
        leftNode.resetHeight();
        leftNode.resetBF();
    }
    
    /*
     * 重置父节点
     * 
     * @param parentNode
     *      父节点
     * @param leftNode
     *      左节点
     */
    private void resetParentNode(Node parentNode, Node leftNode) {
        if (parentNode == null || leftNode == null) {
            return;
        }
        
        if (leftNode.getValue() > parentNode.getValue()) {
            parentNode.setRight(leftNode);
        } else {
            parentNode.setLeft(leftNode);
        }
        
        parentNode.resetHeight();
    }
}
