import java.util.*;

public class InsertInASortedLinkedList {
    public ListNode insert(ListNode head, int value) {
        // Write your solution here
        ListNode newNode = new ListNode(value);
        if (head == null) {
            head = newNode;
        } else if (head.value > value) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode prev = head;
            while (prev.next != null && prev.next.value < value) {
                prev = prev.next;
            }
            newNode.next = prev.next;
            prev.next = newNode;
        }
        return head;
    }
}
