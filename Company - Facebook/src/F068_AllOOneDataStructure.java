import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class F068_AllOOneDataStructure {
    private class Node {
        int val;
        Set<String> keySet;
        Node pre;
        Node next;

        public Node(int val) {
            this.val = val;
            this.keySet = new HashSet<>();
            this.pre = null;
            this.next = null;
        }

        public void insertBefore(Node node) {
            this.pre.next = node;
            node.pre = this.pre;
            node.next = this;
            this.pre = node;
        }

        public void append(Node node) {
            this.next.pre = node;
            node.next = this.next;
            this.next = node;
            node.pre = this;
        }

        public void delete() {
            this.pre.next = this.next;
            this.next.pre = this.pre;
            this.next = null;
            this.pre = null;
        }
    }

    private Map<String, Integer> keyMap;
    private Map<Integer, Node> countMap;
    // Nodes from head to tail have a decreasing frequency
    private Node head;
    private Node tail;

    public F068_AllOOneDataStructure() {
        this.keyMap = new HashMap<>();
        this.countMap = new HashMap<>();
        this.head = new Node(Integer.MAX_VALUE);
        this.tail = new Node(Integer.MIN_VALUE);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (keyMap.containsKey(key)) {
            changeKey(key, 1);
        } else {
            keyMap.put(key, 1);
            if (countMap.containsKey(1)) {
                countMap.get(1).keySet.add(key);
            } else {
                Node node = new Node(1);
                node.keySet.add(key);
                countMap.put(1, node);
                tail.insertBefore(node);
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        Integer val = keyMap.getOrDefault(key, null);
        if (val == null) {
            return;
        }
        Node node = countMap.get(val);
        if (node.val == 1) {
            keyMap.remove(key);
            removeKeyFromNode(node, key);
        } else {
            changeKey(key, -1);
        }
    }

    private void changeKey(String key, int offset) {
        int count = keyMap.get(key);
        keyMap.put(key, count + offset);
        Node countNode = countMap.get(count);
        Node newCountNode = countMap.getOrDefault(count + offset, null);
        // If we encounter a new count, create that node and put it in the right position of the list
        if (newCountNode == null) {
            newCountNode = new Node(count + offset);
            countMap.put(count + offset, newCountNode);
            if (offset == 1) {
                // Count incremented by 1, append it to the right of the original node
                countNode.append(newCountNode);
            } else {
                // Count decremented by 1, insert it to the left of the original node
                countNode.insertBefore(newCountNode);
            }
        }
        newCountNode.keySet.add(key);
        removeKeyFromNode(countNode, key);
    }

    private void removeKeyFromNode(Node node, String key) {
        node.keySet.remove(key);
        if (node.keySet.isEmpty()) {
            countMap.remove(node.val);
            node.delete();
        }
    }

    public String getMaxKey() {
        return head.next == tail ? "" : tail.pre.keySet.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keySet.iterator().next();
    }
}
