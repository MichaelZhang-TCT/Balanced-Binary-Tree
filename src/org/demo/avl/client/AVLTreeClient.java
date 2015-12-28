package org.demo.avl.client;

import java.util.ArrayList;

import org.demo.avl.tree.AVLTree;

public class AVLTreeClient {

    public static void main(String[] args) {
        AVLTreeClient client = new AVLTreeClient();
        client.avlTree();
    }
    
    private void avlTree() {
        int[] nums = {20, 15, 30, 40, 25, 23};
        AVLTree tree = AVLTree.newInstance();
        
        for (int num : nums) {
            tree.insert(num);
        }
        
        tree.show(tree.getRoot(), new ArrayList<>());
    }
}