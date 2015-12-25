package org.demo.avl.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.demo.avl.impl.AdjustLL;
import org.demo.avl.impl.AdjustLR;
import org.demo.avl.impl.AdjustRL;
import org.demo.avl.impl.AdjustRR;
import org.demo.avl.interf.Adjustable;
import org.demo.avl.poke.AdjustUtils;
import org.demo.bst.type.ImbalanceType;

/**
 * <p>
 * 基于平衡二叉树的二叉排序树
 * </p>
 * 2015年12月23日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1.1
 */
public class AVLTree {

    private static AVLTree instance;
    private Node root;
    
    private AVLTree() {
    }
    
    public static AVLTree newInstance() {
        if (instance == null) {
            instance = new AVLTree();
        }
        
        return instance;
    }
    
    /**
     * 重置AVL树的根节点
     * 
     * @param _root
     *      新的根节点
     */
    public void resetRoot(Node _root) {
        this.root = _root;
    }
    
    /**
     * 获得根节点
     * 
     * @return
     *      根节点
     */
    public Node getRoot() {
        return this.root;
    }
    
    /**
     * 向AVL中添加一个新节点
     * 
     * @param _value
     */
    public void insert(int _value) {
        if (root == null) {
            root = new Node(_value);
            root.setHeight(0);
            return;
        }
        
        Node node = root;
        
        boolean inserted = false;
        while(!inserted) {
            if (_value == node.getValue()) {
                inserted = true;
            } else if (_value < node.getValue()) {
                Node leftNode = node.getLeft();
                if (leftNode == null) {
                    leftNode = new Node(_value);
                    modifyTree(node, leftNode);
                    inserted = true;
                } else {
                    node = leftNode;
                }
            }  else {
                Node rightNode = node.getRight();
                if (rightNode == null) {
                    rightNode = new Node(_value);
                    modifyTree(node, rightNode);
                    inserted = true;
                } else {
                    node = rightNode;
                }
            }
        }
    }
    
    /**
     * 显示当前AVL树
     * 
     * @param node
     * @param visited
     */
    public void show(Node node, List<Node> visited) {
        if (node == null || visited.contains(node)) {
            return;
        }
        show(node.getLeft(), visited);
        System.out.println("{" + node.getLeft() + " <- " + node + " -> " + node.getRight() + "}, p = " + node.getParent());
        visited.add(node);
        show(node.getRight(), visited);
    }
    
    /*
     * 修改当前AVL树
     * 
     * @param parentNode
     *      父节点
     * @param childNode
     *      当前节点
     */
    private void modifyTree(Node parentNode, Node childNode) {
        insertNode(parentNode, childNode);
        updateHeight(childNode);
//        checkBalance(childNode);
        Node imbalanceNode = modifyBalanceFactor(childNode);
        if (imbalanceNode != null) {
            ImbalanceType type = catchImbalance(imbalanceNode);
            System.err.println(imbalanceNode + ">" + type);
            adjust(imbalanceNode, type);
        }
    }
    
    /*
     * 插入新节点
     * 
     * @param parent
     * @param newNode
     */
    private void insertNode(Node parent, Node newNode) {
        newNode.setHeight(0);
        newNode.setParent(parent);
        if (newNode.getValue() < parent.getValue()) {
            parent.setLeft(newNode);
        } else if (newNode.getValue() > parent.getValue()) {
            parent.setRight(newNode);
        }
    }
    
    /*
     * 更新AVL中的节点高度
     * 
     * @param newNode
     *      新添加的节点
     */
    private void updateHeight(Node newNode) {
        Node parentNode = newNode.getParent();
        int currentNodeHeight = newNode.getHeight();
        while(parentNode != null) {
            int parentNodeHeight = parentNode.getHeight();
            if (parentNodeHeight > currentNodeHeight) {
                // 无需修改
                break;
            }
            
            parentNode.setHeight(parentNodeHeight + 1);
            parentNode = parentNode.getParent();
            currentNodeHeight = parentNodeHeight + 1;
        }
    }
    
    /*
     * 检查AVL树中的平衡状态
     * 
     * @param newNode
     *      新添加的节点
     */
    private void checkBalance(Node newNode) {
        Node parentNode = newNode.getParent();
        Node leftNode = null;
        Node rightNode = null;
        Node imbalanceNode = null;
        while(parentNode != null) {
            leftNode = parentNode.getLeft();
            rightNode = parentNode.getRight();
            int leftHeight = 0;
            int rightHeight = 0;
            
            // TODO 只有左孩子
            if (leftNode == null) {
                leftHeight = -1;
            } else {
                leftHeight = leftNode.getHeight();
            }
            
            // TODO 只有右孩子
            if (rightNode == null) {
                rightHeight = -1;
            } else {
                rightHeight = rightNode.getHeight();
            }
            
            // 是否平衡
            if (Math.abs(leftHeight - rightHeight) <= 1) {
                parentNode = parentNode.getParent(); // 向上查找
                continue;
            }
            
            // 找到失衡节点
            imbalanceNode = leftHeight > rightHeight ? leftNode : rightNode;
            break;
        }
        
        if (imbalanceNode == null) {
            // 当前是平衡状态
            return;
        }
        
        // TODO 确定失衡状态
        ImbalanceType type = catchImbalanceByNewNode(newNode);
        System.out.println(imbalanceNode + ">" + type);
        adjust(imbalanceNode, type);
    }
    
    /*
     * 检查重置BF并获得不平衡的节点
     * 
     * @param newNode
     *      新添加的节点
     * @return
     *      失衡节点
     */
    private Node modifyBalanceFactor(Node newNode) {
        Node node = newNode;
        Node imbalanceNode = null; // 不平衡的根节点
        do {
            if (node.isLeaf()) {
                node.setBF(0);
                node = node.getParent();
                continue;
            }
            
            int leftHeight = 0;
            int rightHeight = 0;
            Node leftNode = node.getLeft();
            Node rightNode = node.getRight();
            
            leftHeight = leftNode == null ? 0 : leftNode.getHeight() + 1;
            rightHeight = rightNode == null ? 0 : rightNode.getHeight() + 1;
            
            node.setBF(leftHeight - rightHeight);
            if (-1 <= node.getBF() && node.getBF() <= 1) {
                node = node.getParent();
                continue;
            } else {
                imbalanceNode = node;
                break;
            }
        } while (node != null);
        
        return imbalanceNode;
    }
    
    /*
     * 调节AVL树平衡
     * 
     * @param imbalanceNode
     *      失衡节点
     * @param type
     *      失衡状态
     */
    private void adjust(Node imbalanceNode, ImbalanceType type) {
        Map<ImbalanceType, Adjustable> map = getAdjustMap();
        AdjustUtils adjustUtils = new AdjustUtils(map.get(type));
        adjustUtils.adjust(this, imbalanceNode);
    }
    
    /*
     * 获得失衡状态与相应策略之间对应关系的HashMap
     * 
     * @return
     *      HashMap
     */
    private Map<ImbalanceType, Adjustable> getAdjustMap() {
        Map<ImbalanceType, Adjustable> map = new HashMap<>();
        map.put(ImbalanceType.LL, new AdjustLL());
        map.put(ImbalanceType.LR, new AdjustLR());
        map.put(ImbalanceType.RR, new AdjustRR());
        map.put(ImbalanceType.RL, new AdjustRL());
        
        return map;
    }
    
    /*
     * 确定失衡状态
     * 
     * @param newNode
     *      新添加的节点
     * @return
     *      失衡状态
     */
    @Deprecated
    private ImbalanceType catchImbalanceByNewNode(Node newNode) {
        int firstType = 0; // L:-1, R:1
        int secondType = 0; // L:-1, R:1
        
        Node parentNode = newNode.getParent();
        secondType = newNode.getValue() < parentNode.getValue() ? -1 : 1;
        parentNode = parentNode.getParent();
        firstType = newNode.getValue() < parentNode.getValue() ? -1 : 1;
        
        if (firstType < 0 && secondType < 0) {
            return ImbalanceType.LL;
        } else if (firstType < 0 && secondType > 0) {
            return ImbalanceType.LR;
        } else if (firstType > 0 && secondType < 0) {
            return ImbalanceType.RL;
        }
        
        return ImbalanceType.RR;
    }
    
    private ImbalanceType catchImbalance(Node imbalanceNode) {
        int imbalanceBF = imbalanceNode.getBF();
        int childBF = 0;
        if (imbalanceBF < 0) {
            childBF = imbalanceNode.getRight().getBF();
            if (childBF < 0) {
                return ImbalanceType.RR;
            } else if (childBF > 0) {
                return ImbalanceType.RL;
            } else {
                return ImbalanceType.Balance;
            }
        } else if (imbalanceBF > 0) {
            childBF = imbalanceNode.getLeft().getBF();
            if (childBF < 0) {
                return ImbalanceType.LR;
            } else if (childBF > 0) {
                return ImbalanceType.LL;
            } else {
                return ImbalanceType.Balance;
            }
        } else {
            return ImbalanceType.Balance;
        }
    }
}
