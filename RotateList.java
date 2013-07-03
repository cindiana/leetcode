package leetcod;


/*
 * 1. fail {1,2} 0. didn't consider 0.
 * 2. fail {1,2} 2. go thru basic cases to straighten boundaries.
 * 3. fail {1,2,3} 3.
 */
public class RotateList {
	public ListNode rotateRight_20130525(ListNode head, int n) {
        if (n==0) return head;
        if (head==null) return head;
        
        ListNode p1 = head;
        ListNode p2 = head;
        int i=0;
        while (i<n) {
            p1 = p1.next==null? head: p1.next;
            i++;
        }
        
        while(p1.next!=null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        if (p1==p2) return head;
        
        p1.next = head;
        ListNode newHead = p2.next;
        p2.next = null;
        return newHead;
    }
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head==null || head.next==null) return head;
		if (n==0) return head;
		ListNode p1 = head;
		ListNode p2 = head;
		int k = n;
		int listLen = 1;
		while(k>0 && p1.next!=null) {
			p1 = p1.next;
			k--;
			listLen ++;
		}
		if (k>0) {
			k = (k-1)%listLen;
			if (k==0) return head;
			p1 = head;
			while(k>0 && p1.next!=null) {
				p1 = p1.next;
				k--;
			}
		}
	
		while(p1.next!=null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		ListNode newHead = p2.next;
		p2.next = null;
		p1.next = head;
		
		return newHead;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public static void main(String[] args) {
		RotateList rd = new RotateList();
		ListNode n1 = rd.new ListNode(1);
		ListNode n2 = rd.new ListNode(2);
		ListNode n3 = rd.new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		rd.removeNthFromEnd(n1, 3);
	}
}
