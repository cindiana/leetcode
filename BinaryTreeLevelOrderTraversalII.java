package leetcod;

public class BinaryTreeLevelOrderTraversalII {
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root==null) return result;
        
        ArrayList<TreeNode> level1 = new ArrayList<TreeNode>();        
        level1.add(root);
        
        ArrayList<Integer> rootVal = new ArrayList<Integer>();
        rootVal.add(root.val);
        result.add(rootVal);
        
        while(level1.size()>0) {
            ArrayList<TreeNode> level2 = new ArrayList<TreeNode>();
            for (TreeNode node: level1) {
                if (node.left!=null)    level2.add(node.left);
                if (node.right!=null)   level2.add(node.right);
            }
            if (level2.size()>0) {
                ArrayList<Integer> ints = new ArrayList<Integer>();
                for (TreeNode node: level2) {
                    ints.add(node.val);
                }
                result.add(ints);
            }
            level1 = level2;
        }
        
        Collections.reverse(result);
        return result;
    }
}
