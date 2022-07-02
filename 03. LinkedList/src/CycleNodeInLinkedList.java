public class CycleNodeInLinkedList {
    // From start point to cycle start = x;
    // From cycle start to meet point = y;
    // From meet point to cycle start = z;
    // Slow pointer has travelled = x + y
    // Fast pointer has travelled = 2x + 2y = x + y + z + y
    // So we know that x = z
    public ListNode cycleNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode cycleStart = head;
                while (fast != cycleStart) {
                    fast = fast.next;
                    cycleStart = cycleStart.next;
                }
                return cycleStart;
            }
        }
        return null;
    }
}
