package org.demo.bst.type;

/**
 * <p>
 * AVL树中失衡状态枚举
 * </p>
 * 2015年12月24日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.1
 */
public enum ImbalanceType {

    LL("左左失衡"), LR("左右失衡"), RR("右右失衡"), RL("右左失衡");
    
    private String desc;
    
    private ImbalanceType(String _desc) {
        desc = _desc;
    }
    
    public String getDesc() {
        return desc;
    }
}
