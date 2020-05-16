import java.util.HashMap;

public class CopyListWithRandomPtr {
    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private HashMap<Node, Node> visited;
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        this.visited = new HashMap<>();
        Node oldNode = head;
        Node newNode = new Node(head.val);
        visited.put(oldNode, newNode);
        while (newNode != null) {
            newNode.next = getNode(oldNode.next);
            newNode.random = getNode(oldNode.random);
            newNode = newNode.next;
            oldNode = oldNode.next;
        }
        return getNode(head);
    }

    public Node getNode(Node node) {
        if (node == null) {
            return null;
        }
        // If the node we search for is already in the list, just return it, no need to create a new one
        // otherwise, we should create a new node and put it into the map
        Node newNode = visited.getOrDefault(node, null);
        if (newNode == null) {
            newNode = new Node(node.val);
            visited.put(node, newNode);
        }
        return newNode;
    }
}
