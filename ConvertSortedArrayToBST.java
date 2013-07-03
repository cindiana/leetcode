package leetcod;

public class ConvertSortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] num) {
        if (num==null || num.length==0) return null;
        return doBuild(num, 0, num.length-1);
    }
    
    public TreeNode doBuild(int[] num, int start, int end) {
        if (start>end) {
            return null;
        }
        if (start==end) {
            return new TreeNode(num[start]);
        }
        int mid = (start+end)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = doBuild(num, start, mid-1);
        root.right = doBuild(num, mid+1, end);
        return root;
    }
}
}
