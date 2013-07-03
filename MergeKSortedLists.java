package leetcod;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 1. runtime err: node can be null. ArrayList allows null.
 * 2. runtime err: misunderstanding of insertionIndex returned by Collections.binarySearch
 */
public class MergeKSortedLists {
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		if (lists.size() == 1) {
			return lists.get(0);
		}
		ArrayList<MyListNode> sorted = new ArrayList<MyListNode>();
		for (ListNode node : lists) {
			if (node != null) {
				sorted.add(new MyListNode(node));
			}
		}
		Collections.sort(sorted);

		ListNode resultHead = new ListNode(0);// dummy head
		resultHead.next = null;
		ListNode prev = resultHead;
		while (sorted.size() > 0) {
			ListNode min = sorted.get(0).node;
			sorted.remove(0);
			if (min.next != null) {
				MyListNode minNext = new MyListNode(min.next);

				int insertionIndex = Collections.binarySearch(sorted, minNext);
				if (insertionIndex<0) {
					insertionIndex = (insertionIndex+1)*(-1);
				}
				sorted.add(insertionIndex, minNext);
			}

			ListNode next = new ListNode(min.val);
			prev.next = next;
			prev = next;
		}

		return resultHead.next;
	}

	public class MyListNode implements Comparable<MyListNode> {
		ListNode node;

		MyListNode(ListNode n) {
			node = n;
		}

		@Override
		public int compareTo(MyListNode nod) {
			if (node.val == nod.node.val)
				return 0;
			if (node.val < nod.node.val) {
				return -1;
			}
			return 1;
		}

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
		MergeKSortedLists msl = new MergeKSortedLists();
		ListNode n1 = msl.new ListNode(2);
		ListNode n2 = null;
		ListNode n3 = msl.new ListNode(-1);
		ArrayList<ListNode> input = new ArrayList<ListNode>();
		input.add(n1);
		input.add(n2);
		input.add(n3);
		ListNode head = msl.mergeKLists(input);
		while(head!=null) {
			System.out.print(head.val + "-");
			head = head.next;
		}
	}
}
