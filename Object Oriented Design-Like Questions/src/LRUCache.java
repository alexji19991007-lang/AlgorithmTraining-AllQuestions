import java.util.HashMap;
import java.util.Map;

class LRUCache<K, V> {
    // Implement the cache using a doubly linkedList
    class DLinkedNode {
        K key;
        V value;
        DLinkedNode prev;
        DLinkedNode next;
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
        this.removeNode(node);
        this.addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        this.removeNode(res);
        return res;
    }


    private Map<K, DLinkedNode> cache = new HashMap<>();
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

    public V get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return null; // should raise exception here.
        }
        // If we successfully read from the table, we should move this node to the head
        this.moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // if doesn't exist, creat new node
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            // add to map and list, increment mSize
            this.cache.put(key, newNode);
            this.addNode(newNode);
            ++count;
            // is mSize > mCapacity, pop the least recently used one (the one before tail)
            if (count > capacity) {
                // pop the tail
                DLinkedNode tail = this.popTail();
                // don't forget to delete the tail from the map
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }
}
/*
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */