import java.util.HashMap;
import java.util.Map;

// LeetCode 460
public class A021_LFUCache {
    // TC: O(1) for get, O(1) for put
    // SC: O(n) where n is the capacity
    private final int capacity;
    private int size;
    private int min;
    private final Map<Integer, Node> nodeMap;
    private final Map<Integer, DLinkedList> countMap;

    public A021_LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.min = 0;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) {
            return -1;
        }
        update(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.val = value;
            update(node);
        } else {
            Node node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                DLinkedList lastList = countMap.get(min);
                nodeMap.remove(lastList.removeLast().key);
                size--;
            }
            size++;
            min = 1;
            DLinkedList newList = countMap.getOrDefault(node.count, new DLinkedList());
            newList.add(node);
            countMap.put(node.count, newList);
        }
    }

    private void update(Node node) {
        DLinkedList oldList = countMap.get(node.count);
        oldList.remove(node);
        if (node.count == min && oldList.size == 0) {
            min++;
        }
        node.count++;
        DLinkedList newList = countMap.getOrDefault(node.count, new DLinkedList());
        newList.add(node);
        countMap.put(node.count, newList);
    }

    static class DLinkedList {
        Node head, tail;
        int size;

        public DLinkedList() {
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // insert new node after head
        public void add(Node node) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
            size++;
        }

        // remove node from the list
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            size--;
        }

        public Node removeLast() {
            if (size <= 0) {
                return null;
            }
            Node node = tail.prev;
            remove(node);
            return node;
        }
    }

    static class Node {
        int key, val, count;
        Node prev, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.count = 1;
            this.prev = null;
            this.next = null;
        }
    }
}
