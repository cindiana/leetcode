package leetcod;

import java.util.ArrayList;
import java.util.Collections;

public class BinaryTreeZigZagLevelTraversal {
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root==null) return result;
        ArrayList<TreeNode> l1 = new ArrayList<TreeNode>();
        l1.add(root);
        boolean leftFirst = true;
        while(!l1.isEmpty()) {
            ArrayList<TreeNode> l2 = new ArrayList<TreeNode>();
            ArrayList<Integer> l1Ints = new ArrayList<Integer>();
            
            for (TreeNode n : l1) {
                l1Ints.add(n.val);
                
                if (n.left!=null) l2.add(n.left);
                if (n.right!=null) l2.add(n.right);
            }
            
            if (!leftFirst) {
                Collections.reverse(l1Ints);
            }
            
            l1 = l2;
            result.add(l1Ints);
            leftFirst = !leftFirst;
        }
        return result;
    }
}
