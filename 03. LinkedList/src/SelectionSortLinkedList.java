public class SelectionSortLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        SelectionSortLinkedList test = new SelectionSortLinkedList();
        System.out.println(test.selectionSort(head).value);
    }

    public ListNode selectionSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            ListNode smallestNodePrev = searchSmallest(cur);
            ListNode smallestNode = smallestNodePrev.next;
            smallestNodePrev.next = smallestNode.next;
            smallestNode.next = cur.next;
            cur.next = smallestNode;
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode searchSmallest(ListNode node) {
        if (node == null) {
            return null;
        }
        int curMin = Integer.MAX_VALUE;
        ListNode prev = node, cur = prev.next;
        ListNode minPrev = node, minCur = minPrev.next;
        while (cur != null) {
            if (cur.value < curMin) {
                minCur = cur;
                minPrev = prev;
                curMin = cur.value;
            }
            prev = cur;
            cur = cur.next;
        }
        return minPrev;
    }
}
