import java.util.*;

public class CheckIfLinkedListIsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = middleNode(head);
        ListNode secondHalf = reverse(mid.next);
        while (secondHalf != null) {
            if (head.value != secondHalf.value) {
                return false;
            }
            head = head.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
