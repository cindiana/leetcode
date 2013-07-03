package leetcod;

/*
 * 1. fail [1]. sanity check incorrect.
 * ------II--------
 * 1. fail lots. algorithm err.
 * ------List------
 * 1. fail 1-2. out of while loop, attach prevNode as the last node to result
 * ------List II---
 * 2. fail 1-2-2. need to break orig next pointer.
 */
public class RemoveDuplicates {
	public ListNode deleteDuplicates2(ListNode head) {
		if (head==null || head.next==null) return head;
		ListNode dummyHead = new ListNode(0);
		ListNode toReturn = dummyHead;
		
		boolean dup = false;
		int prev = head.val;
		ListNode curr = head.next;
		ListNode prevNode = head;
		while (curr != null) {
			if (curr.val == prev) {
				dup = true;
			} else {
				if (!dup) {
					dummyHead.next = prevNode;
					dummyHead = prevNode;
				}
				dup = false;
				prev = curr.val;
				prevNode = curr;
			}
			curr = curr.next;
		}
		if (!dup){
			dummyHead.next = prevNode;
			dummyHead = dummyHead.next;
		}
		dummyHead.next = null;
		return toReturn.next;
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head != null) {
			int prev = head.val;
			ListNode prevNode = head;
			ListNode curr = head.next;
			while (curr != null) {
				if (curr.val == prev) {
					prevNode.next = curr.next;
				} else {
					prevNode = curr;
					prev = curr.val;
				}
				curr = curr.next;
			}
		}
		return head;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public int removeDuplicatesUp2(int[] A) {
		if (A == null || A.length < 2)
			return A == null ? 0 : A.length;
		int p1 = 1;
		int p2 = 1;
		int prev = A[0];
		while (p1 < A.length) {
			if (A[p1] != prev || p2 == 1 || A[p2 - 1] != A[p2 - 2]) {
				A[p2] = A[p1];
				p2++;
			}
			prev = A[p1];
			p1++;
		}
		return p2;
	}

	public int removeDuplicates(int[] A) {
		if (A == null || A.length < 2)
			return A == null ? 0 : A.length;
		int p1 = 1;
		int p2 = 1;
		int prev = A[0];
		while (p1 < A.length) {
			if (A[p1] != prev) {
				A[p2] = A[p1];
				p2++;
			}
			prev = A[p1];
			p1++;
		}
		return p2;
	}
	
	public ListNode deleteDuplicates_2013(ListNode head) {
        if (head==null || head.next==null) return head;
        
        ListNode myHead = head;
        while(myHead!=null&&myHead.next!=null) {
            if (myHead.val==myHead.next.val) {
                myHead.next = myHead.next.next;
            }
            else {
                myHead = myHead.next;
            }
        }
        return head;
    }
	
	public ListNode deleteDuplicatesII_2013(ListNode head) {
        if (head==null || head.next==null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while(head!=null&&head.next!=null) {
            if (head.val==head.next.val) {
                while(head!=null && head.next!=null && head.val==head.next.val) {
                    head = head.next;
                }
                prev.next = head.next; 
                head = head.next;
            }
            else {
                head = head.next;
                prev = prev.next;
            }
            
        }
        return dummy.next;
        
    }

	public static void main(String[] args) {
		RemoveDuplicates rd = new RemoveDuplicates();
		ListNode n1 = rd.new ListNode(1);
		ListNode n2 = rd.new ListNode(2);
		ListNode n3 = rd.new ListNode(2);
		n1.next = n2;
		n2.next = n3;
		rd.deleteDuplicates2(n1);
	}
}
