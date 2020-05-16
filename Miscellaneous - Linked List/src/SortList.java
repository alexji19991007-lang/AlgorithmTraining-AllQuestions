public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return mergeTwoSorted(l1, l2);
    }

    public ListNode mergeTwoSorted(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode ptr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ptr.next = l1;
                l1 = l1.next;
            } else {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }
        while (l1 != null) {
            ptr.next = l1;
            l1 = l1.next;
            ptr = ptr.next;
        }

        while (l2 != null) {
            ptr.next = l2;
            l2 = l2.next;
            ptr = ptr.next;
        }
        return dummy.next;
    }
}
