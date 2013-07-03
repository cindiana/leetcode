package leetcod;

/*
 * In-order traversal to find nodes that are bigger than its previous.
 * The first time we found one would be the 1st bad node, the next time, the 2nd.
 * Swap 1st and 2nd bad node.
 */
public class RecoverBST {
	public void recoverTree(TreeNode root) {
        if (root==null || root.left==null && root.right==null) return;
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode current = root;
        TreeNode prev = null;
        
        TreeNode bad1 = null;
        TreeNode bad2 = null;
        
        while (!s.isEmpty() && current!=null) {
            while (current.left!=null) {
                s.push(current.left);
                current = current.left;
            }
            TreeNode leftMost = s.pop();
            if (prev!=null) {
                if (leftMost.val<prev.val){
                    if (bad1==null) {
                        bad1 = prev;
                        bad2 = leftMost;
                    }
                    else {
                        bad2 = leftMost;
                    }
                }                
            }
            prev = leftMost;
            
            if (leftMost.right!=null) {
                s.push(leftMost.right);
                current = leftMost.right;
            }
        }  
        
        
        int bad2val = bad2.val;
        bad2.val = bad1.val;
        bad1.val = bad2val;
       
    }
}
