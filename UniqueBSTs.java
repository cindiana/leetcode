package leetcod;

public class UniqueBSTs {
	public int numTrees(int n) {
        if (n<=1) return 1;
        
        int count = 0;
        for (int i=1; i<=n; i++) {
            count+= numTrees(i-1)*numTrees(n-i);
        }
        return count;
    }
	
	public ArrayList<TreeNode> generateTrees(int n) {
        return doWork(1, n);        
    }
    
    public ArrayList<TreeNode> doWork(int s, int e) {
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        
        if (s>e) {
            result.add(null);
            return result;
        }
        if (s==e) {
            result.add(new TreeNode(s));
            return result;
        }
        
        for (int i=s; i<=e; i++) {
            ArrayList<TreeNode> lefts = doWork(s, i-1);
            ArrayList<TreeNode> rights = doWork(i+1, e);
            
            
                for (TreeNode l : lefts) {
                    for (TreeNode r : rights) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        result.add(root);
                    }
                }
            
        }
        return result;
    }
}
