package org.demo.avl.poke;

import org.demo.avl.interf.Adjustable;
import org.demo.avl.tree.AVLTree;
import org.demo.avl.tree.Node;

/**
 * <p>
 * 调节AVL平衡策略的包装类
 * </p>
 * 2015年12月24日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1
 */
public class AdjustUtils {

    private Adjustable adjustable = null;
    
    public AdjustUtils(Adjustable _adjustable) {
        adjustable = _adjustable;
    }
    
    /**
     * 调节AVL树的平衡
     * 
     * @param tree
     *      平衡二叉树
     * @param imbalanceNode
     *      失衡的节点
     */
    public void adjust(AVLTree tree, Node imbalanceNode) {
        adjustable.adjust(tree, imbalanceNode);
    }
}
