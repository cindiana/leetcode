package leetcod;

/*
 * 1. code too slow. add prevCompare to avoid linking already linked nodes.
 * 2. still too slow. avoid all smaller manipulations
 */
public class PartitionList {
	public ListNode partition(ListNode head, int x) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode bigEqualHead = null;
		ListNode bigEqual = null;
		ListNode prev = new ListNode(-1);
		prev.next = head;
		ListNode current = head;
		while (current != null) {
			if (current.val >= x) {
				if (bigEqualHead == null) {
					bigEqualHead = current;
					bigEqual = bigEqualHead;
				} else {
					bigEqual.next = current;
					bigEqual = bigEqual.next;
				}
				prev.next = current.next;
			} else {
				prev = current;
			}
			current = current.next;
		}
		if (bigEqualHead != null) {
			prev.next = bigEqualHead;
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

}
