public class MergeSortLinkedList {
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = middleNode(head);
        ListNode twoHead = mid.next;
        mid.next = null;
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(twoHead);
        return merge(left, right);
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

    public ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode ptr = dummy;
        while (one != null && two != null) {
            if (one.val < two.val) {
                ptr.next = one;
                one = one.next;
            } else {
                ptr.next = two;
                two = two.next;
            }
            ptr = ptr.next;
        }
        if (one != null) {
            ptr.next = one;
        } else {
            ptr.next = two;
        }
        return dummy.next;
    }
}
