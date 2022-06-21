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
    // tail is a dummy tail
    private Node tail;
    // how many non-repeating elements (in the linked list) so far
    private int size;
    // maps the single occurrence of a character to its corresponding node
    private Map<Character, Node> singled;
    // tracks which node has already occurred more than once
    private Set<Character> repeated;

    public FirstNonRepeatingCharacterInStream() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
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

    private void append(Node n) {
        singled.put(n.ch, n);
        Node curLast = tail.prev;
        curLast.next = n;
        n.prev = curLast;
        n.next = tail;
        tail.prev = n;
        size++;
    }

    private void remove(Node n) {
        repeated.add(n.ch);
        singled.remove(n.ch);
        Node prevNode = n.prev;
        Node nextNode = n.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        n.next = null;
        n.prev = null;
        size--;
    }
}
