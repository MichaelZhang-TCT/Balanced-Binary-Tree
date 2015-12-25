package org.demo.avl.client;

import java.util.ArrayList;

import org.demo.avl.tree.AVLTree;

public class BTreeClient {

    public static void main(String[] args) {
        BTreeClient client = new BTreeClient();
        client.avlTree();
    }
    
    private void avlTree() {
        int[] nums = {20, 15, 30,  25, 23};
        AVLTree tree = AVLTree.newInstance();
        
        for (int num : nums) {
            tree.insert(num);
        }
        
        tree.show(tree.getRoot(), new ArrayList<>());
    }
}
