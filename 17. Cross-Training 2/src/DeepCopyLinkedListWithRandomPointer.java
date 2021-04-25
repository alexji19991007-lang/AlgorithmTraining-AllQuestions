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

    static class RandomListNode {
        public int value;
        public RandomListNode next;
        public RandomListNode random;

        public RandomListNode(int value) {
            this.value = value;
        }
    }


}
