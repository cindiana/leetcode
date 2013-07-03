package leetcod;

/*
 * 1. exceed time limit. should break when encountering leftover
 * 2. exceed time limit. algorithm err in k-reverse
 * 3. runtime err. algorithm err in k-reverse
 * 4. exceed time limit. pointer assigned to diff value. keep alert.
 * 5. fail {1,2,3} 2. need to adjust p1 and p2 after k-reverse.
 */
public class ReverseNodeInKGroup {
	public ListNode reverseKGroup_20130527(ListNode head, int k) {
        if (head==null || head.next==null) return head;
        if (k<=1) return head;
        ListNode p1 = head;
        ListNode p2 = head;
        int step = 1;
        while(step<k && p1.next!=null) {
            p1 = p1.next;
            step++;
        }
        if (step<k) return head; //less than k elements from head
        
        ListNode nextUnitStart = p1.next;
        ListNode curr = p2.next;
        ListNode prev = p2;
        while(true) {
            ListNode next = curr.next;
            curr.next = prev;
            if (curr==p1) break;
            prev = curr;
            curr = next;   
        }
        
        p2.next = reverseKGroup(nextUnitStart, k);
        return p1;
    }
	
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k<=0) {
			return head;
		}

		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;

		ListNode p1 = dummyHead;
		ListNode p2 = dummyHead;

		int c = k;
		while (p2.next != null) {
			while (c > 0 && p2.next != null) {
				p2 = p2.next;
				c--;
			}
			if (c == 0) {// found a k group, reverse
				ListNode curr = p1.next;
				ListNode next = curr.next;
				for (int i = 0; i < k - 1; i++) {
					ListNode temp = next.next;					
					next.next = curr;
					curr = next;
					next = temp;
				}

				p1.next.next = next;
				p2 = p1.next;
				p1.next = curr;

				c = k;
				p1 = p2;
			}
			else {
				break;
			}
		}
		return dummyHead.next;

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
		ReverseNodeInKGroup rd = new ReverseNodeInKGroup();
		ListNode n1 = rd.new ListNode(1);
		ListNode n2 = rd.new ListNode(2);
		ListNode n3 = rd.new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		rd.reverseKGroup(n1, 2);
	}
}
