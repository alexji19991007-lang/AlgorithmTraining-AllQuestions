import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstNonRepeatingCharacterInStream {
    static class Node {
        Node prev;
        Node next;
        Character ch;

        Node(Character ch) {
            this.ch = ch;
        }
    }

    // head is a dummy head
    private Node head;
    // tail points to the last element in the list, originally same as head
    private Node tail;
    // maps the single occurrence of a character to its corresponding node
    private Map<Character, Node> singled;
    // tracks which node has already occurred more than once
    private Set<Character> repeated;

    public FirstNonRepeatingCharacterInStream() {
        tail = new Node(null);
        tail.next = tail.prev = tail;
        head = tail;
        singled = new HashMap<>();
        repeated = new HashSet<>();
    }

    public void read(char ch) {
        if (repeated.contains(ch)) {
            return;
        }
        Node node = singled.getOrDefault(ch, null);
        if (node == null) {
            // If hasn't occurred before, add to the list and the singled map
            node = new Node(ch);
            append(node);
        } else {
            // If has already occurred before, remove from the list (finding the node using map)
            // and add it to the repeated set so we will not process it later on.
            remove(node);
        }
    }

    public Character firstNonRepeating() {
        if (head == tail) {
            return null;
        }
        return head.next.ch;
    }

    private void append(Node node) {
        singled.put(node.ch, node);
        tail.next = node;
        node.prev = tail;
        node.next = head;
        tail = tail.next;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        if (node == tail) {
            tail = node.prev;
        }
        node.prev = node.next = null;
        repeated.add(node.ch);
        singled.remove(node.ch);
    }
}
