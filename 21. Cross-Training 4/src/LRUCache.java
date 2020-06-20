import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    class DLinkedNode {
        K key;
        V value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    public Map<K, DLinkedNode> cache = new HashMap<>();
    private int mSize;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int limit) {
        this.mSize = 0;
        this.capacity = limit;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        tail.prev = head;
        head.next = tail;
    }

    public void set(K key, V value) {
        DLinkedNode node = cache.getOrDefault(key, null);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode);
            mSize++;
            if (mSize > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                mSize--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public V get(K key) {
        DLinkedNode node = cache.getOrDefault(key, null);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
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
}
