package leetcod;

public class ConstructBinaryTreeFromPreorderInorder {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
	       if (inorder==null || inorder.length==0 || preorder==null || preorder.length==0) return null;
	        return doWork(inorder, 0, inorder.length-1, preorder, 0, preorder.length-1);
	    }
	    
	    public TreeNode doWork(int[] inorder, int istart, int iend, int[] preorder, int pstart, int pend) {
	        if (istart>iend) return null;
	        if (istart==iend) {
	            return new TreeNode(inorder[istart]);
	        }
	        
	        TreeNode root = new TreeNode(preorder[pstart]);
	        int rootIndex = -1;
	        for (int i=istart; i<=iend; i++) {
	            if (preorder[pstart]==inorder[i]) {
	                rootIndex = i;
	                break;
	            }
	        }
	        root.left = doWork(inorder, istart, rootIndex-1, preorder, pstart+1, pstart+rootIndex-istart);
	        root.right = doWork(inorder, rootIndex+1, iend, preorder, pstart+rootIndex-istart+1, pend);
	        return root;
	    }
}
