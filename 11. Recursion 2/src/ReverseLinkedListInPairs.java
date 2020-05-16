public class ReverseLinkedListInPairs {
    public ListNode reverseInPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseInPairs(head.next.next);
        ListNode temp = head.next;
        temp.next = head;
        head.next = newHead;
        return temp;
    }
}
