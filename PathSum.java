package leetcod;

import java.util.ArrayList;

public class PathSum {
	 public boolean hasPathSum_20130602(TreeNode root, int sum) {
	        if (root==null) return false;
	        return dowork(root, sum, 0);
	    }
	    
	    public boolean dowork(TreeNode root, int sum, int passedDown) {
	        if (root.left==null && root.right==null) {
	            if (root.val+passedDown==sum) return true;
	            return false;
	        }
	        
	        if (root.left!=null && dowork(root.left, sum, passedDown+root.val)) return true;
	        if (root.right!=null && dowork(root.right, sum, passedDown+root.val)) return true;
	        return false;
	    }
	    
	public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null) return false;
        if (root.left==null && root.right==null && root.val!=sum) return false;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            TreeNode localRoot = queue.poll();
            if (localRoot.left==null && localRoot.right==null) {
                if (localRoot.val==sum) return true;
                continue;
            }
            if (localRoot.left!=null) {
                TreeNode sumNode = new TreeNode(localRoot.val+localRoot.left.val);
                sumNode.left = localRoot.left.left;
                sumNode.right = localRoot.left.right;
                
                queue.offer(sumNode);
            }
            if (localRoot.right!=null) {
                TreeNode sumNode = new TreeNode(localRoot.val+localRoot.right.val);
                sumNode.left = localRoot.right.left;
                sumNode.right = localRoot.right.right;
                
                queue.offer(sumNode);
            }
        }
        return false;
        
    }
	
	public ArrayList<ArrayList<Integer>> pathSumII(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root==null) return result;
        if (root.left==null && root.right==null && root.val!=sum) return result;
        
        Queue<TreeNodeWPath> queue = new LinkedList<TreeNodeWPath>();
        ArrayList<Integer> justMe = new ArrayList<Integer>();
        justMe.add(root.val);
        TreeNodeWPath rootWPath = new TreeNodeWPath(root.val);
        rootWPath.path = justMe;
        rootWPath.left = root.left;
        rootWPath.right = root.right;
        queue.offer(rootWPath);
        
        while(!queue.isEmpty()) {
            TreeNodeWPath localRoot = queue.poll();
            if (localRoot.left==null && localRoot.right==null) {
                if (localRoot.val==sum) 
                {
                    result.add(localRoot.path);    
                }
            }
            if (localRoot.left!=null) {
                TreeNodeWPath sumNode = new TreeNodeWPath(localRoot.val+localRoot.left.val);
                sumNode.path = new ArrayList<Integer>();
                for (Integer intt: localRoot.path) {
                    sumNode.path.add(intt);
                }
                sumNode.path.add(localRoot.left.val);
                sumNode.left = localRoot.left.left;
                sumNode.right = localRoot.left.right;
                
                queue.offer(sumNode);
            }
            if (localRoot.right!=null) {
                TreeNodeWPath sumNode = new TreeNodeWPath(localRoot.val+localRoot.right.val);
                sumNode.path = new ArrayList<Integer>();
                for (Integer intt: localRoot.path) {
                    sumNode.path.add(intt);
                }
                sumNode.path.add(localRoot.right.val);
                sumNode.left = localRoot.right.left;
                sumNode.right = localRoot.right.right;
                
                queue.offer(sumNode);
            }
        }
        return result;
    }
	
	 public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
    
    public class TreeNodeWPath {
      int val;
      TreeNode left;
      TreeNode right;
      ArrayList<Integer> path;
      TreeNodeWPath(int x) { val = x;}
  }
}
