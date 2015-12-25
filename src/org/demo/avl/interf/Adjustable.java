package org.demo.avl.interf;

import org.demo.avl.tree.AVLTree;
import org.demo.avl.tree.Node;

/**
 * <p>
 * 调整AVL树的公共接口
 * </p>
 * <p>
 * 所有旋转调节的策略都需要具备调节的能力
 * </p>
 * 2015年12月24日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1
 */
public interface Adjustable {

    /**
     * 调节AVL树的平衡
     * 
     * @param tree
     *      平衡二叉树
     * @param imbalanceNode
     *      失衡的节点
     */
    public void adjust(AVLTree tree, Node imbalanceNode);
}
