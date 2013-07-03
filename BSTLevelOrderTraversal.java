package leetcod;

import java.util.ArrayList;

public class BSTLevelOrderTraversal {
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root==null) return result;
        
        List<TreeNode> l1 = new ArrayList<TreeNode>();
        List<TreeNode> l2 = new ArrayList<TreeNode>();
        
        l1.add(root);
        
        while(l1.size()>0) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                while(l1.size()>0) {
                    TreeNode n = l1.get(0);
                    l1.remove(0);
                    if (n.left!=null) l2.add(n.left);
                    if (n.right!=null) l2.add(n.right);
                    row.add(n.val);
                }
                // now l1 is empty
                result.add(row);
                l1 = l2;
                l2 = new ArrayList<TreeNode>();
        }
        return result;
    }
}
