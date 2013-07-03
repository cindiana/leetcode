package leetcod;

public class ValidBST {
	public boolean isValidBST(TreeNode root) {
        
        return doCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public boolean doCheck(TreeNode root, int min, int max) {
        if (root==null) return true;
        if (min>=root.val || max<=root.val) return false;
        return doCheck(root.left, min, root.val) && doCheck(root.right, root.val, max);
    }
}
