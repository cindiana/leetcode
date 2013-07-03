package leetcod;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FlattenBST {
	public void flatten_20130507(TreeNode root) {
        if (root==null || root.left==null && root.right==null) return;
        if (root.left!=null) {
            flatten(root.left);
        }
        if (root.right!=null) {
            flatten(root.right);
        }
        if (root.left!=null) {
            if (root.right==null) {
                root.right = root.left;
                root.left = null;
            }
            else {
                TreeNode right = root.right;
                root.right = root.left;
                TreeNode leftRoot = root.left;
                while(leftRoot.right!=null) {
                    leftRoot = leftRoot.right;
                }
                leftRoot.right = right;
                root.left = null;
            }
        }
    }
	public void flatten(TreeNode root) {
        if (root==null || root.left==null && root.right==null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        /*TreeNode current = root;
        while(!stack.isEmpty()) {
            
            while(current.left!=null){
                stack.push(current.left);
                current = root.left;
            }
        
            TreeNode visiting = stack.pop();
            flatTreeTail.right = new TreeNode(visiting.val);
            flatTreeTail = flatTreeTail.right;
            
            if (visiting.right!=null) {
               stack.push(visiting.right);
               current = visiting.right;
            }
        }
        root = flatTreeHead.right;*/
        List<TreeNode> result = new ArrayList<TreeNode>();
        doFlatten(root, result);
        for (int i=0; i<result.size(); i++) {
        	System.out.println(result.get(i).val);
            result.get(i).left = null;
            if (i<result.size()-1) {
                result.get(i).right = result.get(i+1);
            }
        }
        
        root = result.get(0);
        
        
    }
    public void doFlatten(TreeNode root, List<TreeNode> result) {
        if (root==null) return;
        doFlatten(root.left, result);
        result.add(root);
        doFlatten(root.right, result);        
        
    }
	
	public class TreeNode {
		     int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
	}
	
	public static void main(String[] args) {
		FlattenBST me = new FlattenBST();
		TreeNode root = me.new TreeNode(2);
		TreeNode one = me.new TreeNode(1);
		root.left = one;
		me.flatten(root);
	}
}
