import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    static class DLinkedNode<K, V> {
        K key;
        V value;
        DLinkedNode<K, V> prev;
        DLinkedNode<K, V> next;
    }

    public Map<K, DLinkedNode<K, V>> cache;
    private int mSize;
    private final int capacity;
    private final DLinkedNode<K, V> head;
    private final DLinkedNode<K, V> tail;

    public LRUCache(int limit) {
        this.mSize = 0;
        this.capacity = limit;
        cache = new HashMap<>();
        head = new DLinkedNode<>();
        tail = new DLinkedNode<>();
        tail.prev = head;
        head.next = tail;
    }

    public void set(K key, V value) {
        DLinkedNode<K, V> node = cache.getOrDefault(key, null);
        if (node == null) {
            DLinkedNode<K, V> newNode = new DLinkedNode<K, V>();
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode);
            mSize++;
            if (mSize > capacity) {
                DLinkedNode<K, V> tail = popTail();
                cache.remove(tail.key);
                mSize--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public V get(K key) {
        DLinkedNode<K, V> node = cache.getOrDefault(key, null);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    private void addNode(DLinkedNode<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode<K, V> node) {
        DLinkedNode<K, V> before = node.prev;
        DLinkedNode<K, V> after = node.next;
        before.next = after;
        after.prev = before;
        node.prev = null;
        node.next = null;
    }

    private void moveToHead(DLinkedNode<K, V> node) {
        this.removeNode(node);
        this.addNode(node);
    }

    private DLinkedNode<K, V> popTail() {
        DLinkedNode<K, V> res = tail.prev;
        this.removeNode(res);
        return res;
    }
}
