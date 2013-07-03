package leetcod;

public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
        if (root==null || root.left==null && root.right==null) {
            return true;
        }
        if (root.right!=null ^ root.left!=null) return false;
        return doSym(root.left, root.right);
    }
    
    public boolean doSym(TreeNode left, TreeNode right) {
        if (left==null ^ right==null) return false;
        if (left==null && right==null) return true;
        if (left.val!=right.val) return false;
        if (left.left==null&&left.right==null && right.left==null&&right.right==null) return true;
        return doSym(left.left, right.right) && doSym(left.right, right.left);
    }
}
