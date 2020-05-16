public class Node<K, V> {
    final K key;
    V value;
    Node<K,V> next;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V newValue) {
        this.value = newValue;
    }
}
