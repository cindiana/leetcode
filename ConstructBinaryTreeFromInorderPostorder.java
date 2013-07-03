package leetcod;

public class ConstructBinaryTreeFromInorderPostorder {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder==null || inorder.length==0 || postorder==null || postorder.length==0) return null;
        return doWork(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    public TreeNode doWork(int[] inorder, int istart, int iend, int[] postorder, int pstart, int pend) {
        if (istart>iend) return null;
        if (istart==iend) {
            return new TreeNode(inorder[istart]);
        }
        
        TreeNode root = new TreeNode(postorder[pend]);
        int rootIndex = -1;
        for (int i=istart; i<=iend; i++) {
            if (postorder[pend]==inorder[i]) {
                rootIndex = i;
                break;
            }
        }
        root.left = doWork(inorder, istart, rootIndex-1, postorder, pstart, pstart+rootIndex-istart-1);
        root.right = doWork(inorder, rootIndex+1, iend, postorder, pstart+rootIndex-istart, pend-1);
        return root;
    }
}
