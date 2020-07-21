import java.util.*;

public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int SCALE_FACTOR = 2;

    private Node<K, V>[] array;
    private int size;
    private float loadFactor;

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity Cannot Be <= 0");
        }
        this.array = (Node<K, V>[])(new Node[capacity]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean containsValue(V value) {
        if (isEmpty()) {
            return false;
        }
        for (Node<K, V> node : array) {
            while (node != null) {
                if (equalsValue(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    // The return value is the original value corresponding to the key
    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = array[index];
        Node<K, V> node = head;
        // Case 1: If the key already exists in the hash map
        while (node != null) {
            if (equalsKey(node.key, key)) {
                V res = node.value;
                node.value = value;
                return res;
            }
            node = node.next;
        }
        // Case 2: If the key does not exists in the hash map
        // Append the new node before the head and update the new head.
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
        // Rehash if needed.
        if (needsRehashing()) {
            rehashing();
        }
        return null;
    }

    // The return value is the original value corresponding to the key.
    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        Node<K, V> pre = null;
        while (node != null) {
            if (equalsKey(node.key, key)) {
                if (pre != null) {
                    pre.next = node.next;
                } else {
                    array[index] = node.next;
                }
                size--;
                return node.value;
            }
            pre = node;
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() & 0x7FFFFFFF; // guarantee non-negative
    }

    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    private boolean equalsValue(V v1, V v2) {
        return Objects.equals(v1, v2);
    }

    private boolean equalsKey(K k1, K k2) {
        return Objects.equals(k1, k2);
    }

    private boolean needsRehashing() {
        float ratio = (size + 0.0f) / array.length;
        return ratio >= loadFactor;
    }

    private void rehashing() {
        Node<K, V>[] oldArray = array;
        array = (Node<K, V>[]) (new Node[array.length * SCALE_FACTOR]);
        for (Node<K, V> node : oldArray) {
            while (node != null) {
                Node<K, V> nextNode = node.next;
                int index = getIndex(node.key);
                node.next = array[index];
                array[index] = node;
                node = nextNode;
            }
        }
    }
}
