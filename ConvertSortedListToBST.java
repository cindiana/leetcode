package leetcod;

public class ConvertSortedListToBST {
	public TreeNode sortedListToBST(ListNode head) {
        if (head==null) return null;
        int length = 0;
        ListNode temp = head;
        while(temp!=null) {
            length++;
            temp = temp.next;
        }
        
        return doBuild(head, 0, length-1);
    }
    
    public TreeNode doBuild(ListNode head, int start, int end) {
        if (start>end) return null;
        int mid = (start+end)/2;
        TreeNode left = doBuild(head, start, mid-1);
        TreeNode root = new TreeNode(head.val);
        root.left = left;
        if (head.next!=null) {//must actually change the content, otherwise java stack will mess up "pass by reference"
            head.val = head.next.val;
            head.next = head.next.next;
        }
        root.right = doBuild(head, mid+1, end);
        return root;
    }
}
