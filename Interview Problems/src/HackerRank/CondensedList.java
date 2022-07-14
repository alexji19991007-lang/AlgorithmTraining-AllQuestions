package HackerRank;

import java.util.HashSet;
import java.util.Set;

public class CondensedList {
    public static void main(String[] args) {
        CondensedList test = new CondensedList();
        ListNode head = new ListNode(3);
        ListNode one = new ListNode(4);
        head.next = one;
        ListNode two = new ListNode(3);
        one.next = two;
        ListNode three = new ListNode(3);
        two.next = three;
        ListNode four = new ListNode(6);
        three.next = four;
        four.next = new ListNode(4);
        head = test.removeDuplicatedNodes(head);
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }

    public ListNode removeDuplicatedNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> occurredNum = new HashSet<>();
        occurredNum.add(head.value);
        ListNode prev = head, cur = head.next;
        while (cur != null) {
            if (!occurredNum.add(cur.value)) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
