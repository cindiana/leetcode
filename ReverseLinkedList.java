package leetcod;

public class ReverseLinkedList {
	public ListNode reverseBetween_20130615(ListNode head, int m, int n) {
        ListNode mp = head;
        ListNode np = head;
        ListNode mpprev = null;
        for (int i=1; i<m; i++) {mpprev = mp; mp = mp.next;}
        for (int i=1; i<n; i++) np = np.next;
        ListNode npnext = np.next;
        
        ListNode prev = mp;
        ListNode curr = mp.next;
        while(prev!=np) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        if (m==1) {
            head.next = npnext;    
            return np;
        }
        mpprev.next = np;
        mp.next = npnext;
        return head;
    }
	public ListNode reverseBetween_2013(ListNode head, int m, int n) {
        ListNode p1 = head;
        ListNode prefix = head;        
        int i = 1;
        while(i<m) {
            prefix = p1;
            p1 = p1.next;
            i++;
        }
        ListNode mNode = p1;
        ListNode prev;
        ListNode next = p1.next;
        while(i<n){
            prev = p1;
            p1 = next;
            next = next.next;
            
            p1.next = prev;
            i++;
        }
        
        ListNode nNode = p1;
        ListNode tail = next;
        mNode.next = tail;
        if (prefix!=mNode) {
            prefix.next = nNode;
            return head;
        }
        
        return nNode;
    }
	
	public static void main(String[] args) {
		ReverseLinkedList rll = new ReverseLinkedList();
		ListNode n1 = rll.new ListNode(-1);
		ListNode n2 = rll.new ListNode(-3);
		n1.next = n2;
		
		rll.reverseBetween_2013(n1, 1, 2);
	}
	
	
	 public class ListNode {
		     int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
		  }
}
