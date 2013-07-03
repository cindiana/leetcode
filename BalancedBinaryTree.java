package leetcod;

public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {
        return doBalance(root).isBalanced;
    }
    public Tuple doBalance(TreeNode root) {
        if (root==null) return new Tuple(0, true);
        if (root.left==null&&root.right==null) return new Tuple(1, true);
        
        Tuple l = doBalance(root.left);
        Tuple r = doBalance(root.right);
        if (!l.isBalanced || !r.isBalanced) return new Tuple(-1, false);
        return new Tuple(Math.max(l.height, r.height)+1, Math.abs(l.height-r.height)<2);
    }
    
    
    public class Tuple {
        public int height;
        public boolean isBalanced;
        public Tuple(int h, boolean i) {
            height = h;
            isBalanced = i;
        }
    }
}
