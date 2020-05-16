public class DeleteLastOccurrence {
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(2);
        ListNode fourth = new ListNode(3);
        ListNode fifth = new ListNode(2);
        ListNode sixth = new ListNode(1);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = null;
        deleteLast(first, 2);
    }

    public static void deleteLast(ListNode head, int dig) {
        if (dig < 0 || dig >= 9) {
            return;
        }
        if (head == null) {
            return;
        }
        ListNode cur = head;
        ListNode lastOccur = null;
        ListNode lastOccurPrev = null;
        if (cur.val == dig && cur.next == null) {
            head = head.next;
        }
        while (cur.next != null) {
            if (cur.next.val == dig) {
                lastOccur = cur.next;
                lastOccurPrev = cur;
            }
            cur = cur.next;
        }
        lastOccurPrev.next = lastOccur.next;
    }
}
