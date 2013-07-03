package leetcod;

public class BinaryTreeMaxSumPath {
	public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        
        ArrayList<Integer> run = new ArrayList<Integer>();
        run.add(root.val);
        int pathMax = doWork(root, run);
        return Math.max(pathMax, run.get(0));
    }
    
    public int doWork(TreeNode root, ArrayList<Integer> max) {
        if (root.left==null && root.right==null) return root.val;
        
        int left = 0;
        int right = 0;
        if (root.left!=null) {
            left = doWork(root.left, max);            
        }
        if (root.right!=null) {
            right = doWork(root.right, max);            
        }
        
        int selectMax = Math.max(Math.max(left, right) + root.val, root.val);
        
        int allMax = Math.max(selectMax, left+right+root.val);
        if (root.left!=null) allMax = Math.max(allMax, left);
        if (root.right!=null) allMax = Math.max(allMax, right);
        if (allMax>max.get(0)) {
            max.clear(); 
            max.add(allMax);
        }
        
        return selectMax;
    }
}
