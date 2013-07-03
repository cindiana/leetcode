package leetcod;

/*
 * 1. runtime err. need to define base case.
 */
public class SwapNodesInPair {
	public ListNode swapPairs_20130527(ListNode head) {
        if (head==null || head.next==null) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode prevUnitTail = dummy;
        
        while (head!=null && head.next!=null) {
            prevUnitTail.next = head.next;
            prevUnitTail = head;
            ListNode nextUnitHead = head.next.next;
            head.next.next = head;
            head = nextUnitHead;
        }
        prevUnitTail.next = head;
        
        return dummy.next;
    }
	
	public ListNode swapPairs(ListNode head) {
		if (head==null || head.next==null) return head;
		
		return doSwap(head);
	}

	private ListNode doSwap(ListNode head) {
		if (head==null || head.next==null) return head;
		ListNode temp = head.next;
		ListNode nextSwapStart = temp.next;
		temp.next = head;
		head.next = doSwap(nextSwapStart);
		return temp;
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
