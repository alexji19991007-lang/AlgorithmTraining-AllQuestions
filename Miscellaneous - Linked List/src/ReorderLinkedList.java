public class ReorderLinkedList {
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = middleNode(head);
        ListNode one = head;
        ListNode two = reverse(mid.next);
        mid.next = null;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (one != null || two != null) {
            if (one != null) {
                cur.next = one;
                cur = cur.next;
                one = one.next;
            }
            if (two != null) {
                cur.next = two;
                cur = cur.next;
                two = two.next;
            }
        }
        return dummy.next;
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
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        return prev;
    }
}
