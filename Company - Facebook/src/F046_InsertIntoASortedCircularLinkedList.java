// LeetCode 708
public class F046_InsertIntoASortedCircularLinkedList {
    // TC: O(n)
    // SC: O(1)
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal, null);
            newNode.next = newNode;
            return newNode;
        }
        Node prev = head;
        boolean inserted = false;
        while (prev.next != head && !inserted) {
            Node cur = prev.next;
            if (prev.val <= insertVal && insertVal <= cur.val) {
                insertBetween(insertVal, prev, cur);
                inserted = true;
            } else if (prev.val > cur.val) {
                if (insertVal >= prev.val || insertVal <= cur.val) {
                    insertBetween(insertVal, prev, cur);
                    inserted = true;
                }
            }
            prev = prev.next;
        }
        if (!inserted) {
            insertBetween(insertVal, prev, prev.next);
        }
        return head;
    }

    public void insertBetween(int value, Node prev, Node cur) {
        Node newNode = new Node(value);
        prev.next = newNode;
        newNode.next = cur;
    }

    static class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
}
