package leetcod;

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;
        int carry = 0;
        int sum = 0;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(l1!=null && l2!=null) {
            sum = l1.val+l2.val+carry;
            carry = sum/10;
            tail.next = new ListNode(sum%10);
            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1!=null) {
            sum = l1.val+carry;
            carry = sum/10;
            tail.next = new ListNode(sum%10);
            tail = tail.next;
            l1 = l1.next;
        }
        while(l2!=null) {
            sum = l2.val+carry;
            carry = sum/10;
            tail.next = new ListNode(sum%10);
            tail = tail.next;
            l2 = l2.next;
        }
        if (carry!=0) {
            ListNode newNode = new ListNode(carry);
            tail.next = newNode;
        }
        return dummy.next;
    }
}
