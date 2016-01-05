package org.demo.avl.client;

import java.util.ArrayList;

import org.demo.avl.tree.AVLTree;

public class AVLTreeClient {

    public static void main(String[] args) {
        AVLTreeClient client = new AVLTreeClient();
        client.avlTree();
    }
    
    private void avlTree() {
        int[] nums = {50, 30, 55, 40, 20, 45};
        AVLTree tree = AVLTree.newInstance();
        
        for (int num : nums) {
            tree.insert(num);
        }
        
        tree.show(tree.getRoot(), new ArrayList<>());
    }
}
