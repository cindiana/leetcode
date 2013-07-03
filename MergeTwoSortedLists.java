package leetcod;

/*
 * 1. time limit exceeded for big test. Add line 35 to step faster, then pass.
 */
public class MergeTwoSortedLists {
	public ListNode mergeTwoLists_20130505(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;
        
        ListNode head = null;
        ListNode tail = null;
        while(l1!=null && l2!=null) {
            if (l1.val<l2.val) {
                if (head==null) {
                    head = l1;
                    tail = l1;
                }
                else {
                    tail.next = l1;
                    tail = l1;
                }
                l1 = l1.next;
            }
            else {
                if (head==null) {
                    head = l2;
                    tail = l2;
                }
                else {
                    tail.next = l2;
                    tail = l2;
                }
                l2 = l2.next;
            }
        }
        while(l1!=null) {
           tail.next = l1;
           tail = l1;
           l1 = l1.next;
        }
        while(l2!=null) {
            tail.next = l2;
            tail = l2;
            l2 = l2.next;
        }
        tail.next = null;
        return head;
        
    }
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1==null) {
			return l2;
		}
		if (l2==null) {
			return l1;
		}
		
		ListNode toReturn;
		ListNode prev;
		ListNode other;
		if(l1.val<l2.val) {
			prev = l1;
			other = l2;
		}
		else {
			prev = l2;
			other = l1;
		}
		toReturn = prev;
		
		while(prev.next!=null && other!=null){
			if (prev.next.val<other.val) {
				prev = prev.next;
			}
			else{
				ListNode temp = prev.next;
				prev.next = other;
				prev = prev.next;
				other = temp;
			}
		}
		if (other!=null) {
			prev.next = other;
		}

		return toReturn;
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
