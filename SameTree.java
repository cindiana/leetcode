package leetcod;

public class SameTree {
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null) return true;
        if (p==null || q==null) return false;
        if (p.val!=q.val) return false;
        if (p.left==null ^ q.left==null) return false;
        if (p.right==null ^ q.right==null) return false;
                       
        if (p.left!=null && q.left!=null) if (!isSameTree(p.left, q.left)) return false;
        if (p.right!=null && q.right!=null) if (!isSameTree(p.right, q.right)) return false;
        
        return true;
    }
}
