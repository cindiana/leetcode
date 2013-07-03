package leetcod;

/**
 * 1. forgot to adjust for loop boundary to the fact that head(index=0) is pre-fetched.
 * 2. err: used index=0 in result list as head. Should use index=(size-1).
 * 3. Read problem completely wrong. Didn't verify with example. Numbers start with smallest digit
 * 4. plain typo, reversed assign. Do not panic. Rewriting correct code is better than not realizing error.
 * 5. didn't fix the same assign reverse in 1st while loop, after fixing in the 2nd and 3rd loops.
 *    Always check to see if a fix could be applied somewhere else.
 */
public class AddLinkedListNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null) {
            return l2;
        }
        if (l2==null) {
            return l1;
        }
        
        int temp = l1.val+l2.val;
        int digit = temp%10;
        int carry = temp/10;
        
        ListNode head = new ListNode(digit);
        ListNode prev = head;
        while(l1.next!=null&&l2.next!=null) {
            l1 = l1.next;
            l2 = l2.next;
            
            int sum = l1.val+l2.val+carry;
            digit = sum%10;
            carry = sum/10;
            ListNode node = new ListNode(digit);
            prev.next = node;
            prev = node;
        }
        
        while (l1.next!=null) {            
            l1 = l1.next;
            int sum = l1.val+carry;
            digit = sum%10;
            carry = sum/10;
            ListNode node = new ListNode(digit);
            prev.next = node;
            prev = node;
        }
        
        while (l2.next!=null) {
            l2 = l2.next;
            int sum = l2.val+carry;
            digit = sum%10;
            carry = sum/10;
            ListNode node = new ListNode(digit);
            prev.next = node;
            prev = node;
        }
        
        if (carry>0) {
            ListNode node = new ListNode(carry);
            prev.next = node;
        }
        
        return head;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
