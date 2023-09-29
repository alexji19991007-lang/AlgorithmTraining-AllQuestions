import java.util.HashMap;
import java.util.Map;

public class DeepCopyLinkedListWithRandomPointer {
    public RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> visited = new HashMap<>();
        RandomListNode oldNode = head;
        RandomListNode newNode = new RandomListNode(head.value);
        RandomListNode newHead = newNode;
        visited.put(oldNode, newNode);
        while (newNode != null) {
            newNode.next = getNode(oldNode.next, visited);
            newNode.random = getNode(oldNode.random, visited);
            newNode = newNode.next;
            oldNode = oldNode.next;
        }
        return newHead;
    }

    public RandomListNode getNode(RandomListNode node, Map<RandomListNode, RandomListNode> visited) {
        if (node == null) {
            return null;
        }
        RandomListNode res = visited.getOrDefault(node, null);
        if (res == null) {
            res = new RandomListNode(node.value);
            visited.put(node, res);
        }
        return res;
    }


    // Follow up: What if we want to avoid any extra space cost?
    // Answer:
    // 1. We can first create the new linked list "inside" the original linked list.
    //    Example (without showing random ptr):
    //    A --> B --> C --> D --> null
    //    A --> A' --> B --> B' --> C --> C' --> D --> D' --> null
    // 2. After having the above list, we establish the new list's random pointers.
    //    Suppose node cur is a node in the old list:
    //    cur.next.random = cur.random != null ? cur.random.next : null;
    //    cur = cur.next.next;
    // 3. Finally, we separate the new linked list from the old linked list.
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode newNode = new RandomListNode (cur.value);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }

        cur = head;
        while (cur != null) {
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }

        RandomListNode oldListPointer = head;
        RandomListNode newListPointer = head.next;
        RandomListNode newHead = head.next;
        while (oldListPointer != null) {
            oldListPointer.next = oldListPointer.next.next; // We don't do null check here because we are very sure there must be a duplicate node after the current old node
            newListPointer.next = newListPointer.next != null ? newListPointer.next.next : null;
            oldListPointer = oldListPointer.next;
            newListPointer = newListPointer.next;
        }
        return newHead;
    }

    static class RandomListNode {
        public int value;
        public RandomListNode next;
        public RandomListNode random;

        public RandomListNode(int value) {
            this.value = value;
        }
    }
}
