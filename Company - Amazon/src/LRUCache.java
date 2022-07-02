import java.util.HashMap;
import java.util.Map;

// LeetCode 146
public class LRUCache {
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int count; // mSize
    private int capacity; // mCapacity
    // Here we have two dummy nodes, head and tail, such that addition and removal are made easier
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        // Originally, we only have two dummy nodes, head & tail
        head = new DLinkedNode();
        head.prev = null;

        tail = new DLinkedNode();
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1; // should raise exception here.
        }
        // If we successfully read from the table, we should move this node to the head
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // if doesn't exist, creat new node
            DLinkedNode newNode = new DLinkedNode(key, value);
            // add to map and list, increment mSize
            cache.put(key, newNode);
            addNode(newNode);
            count++;
            // is mSize > mCapacity, pop the least recently used one (the one before tail)
            if (count > capacity) {
                // pop the tail
                DLinkedNode tail = popTail();
                // don't forget to delete the tail from the map
                cache.remove(tail.key);
                count--;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    }

    private void addNode(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode before = node.prev;
        DLinkedNode after = node.next;
        before.next = after;
        after.prev = before;
        node.prev = null;
        node.next = null;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    // Implement the cache using a doubly linkedList
    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
