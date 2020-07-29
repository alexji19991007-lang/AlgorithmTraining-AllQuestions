import java.util.*;

// LeetCode 133
public class F052_CloneGraph {
    // Method 1: BFS
    // TC: O(n)
    // SC: O(n)
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        Map<Node, Node> oldToNew = new HashMap<>();
        Node newStart = new Node(node.val);
        q.offer(node);
        oldToNew.put(node, newStart);
        while (!q.isEmpty()) {
            Node oldNode = q.poll();
            for (Node oldNeighborNode : oldNode.neighbors) {
                Node newNeighborNode = oldToNew.getOrDefault(oldNeighborNode, null);
                if (newNeighborNode == null) {
                    newNeighborNode = new Node(oldNeighborNode.val);
                    q.offer(oldNeighborNode);
                    oldToNew.put(oldNeighborNode, newNeighborNode);
                }
                oldToNew.get(oldNode).neighbors.add(newNeighborNode);
            }
        }
        return newStart;
    }

    // Method 2: DFS
    public Node cloneGraph_DFS(Node node) {
        if (node == null) {
            return null;
        }
        Node newStart = new Node(node.val);
        Map<Node, Node> oldToNew = new HashMap<>();
        oldToNew.put(node, newStart);
        DFS(node, oldToNew);
        return newStart;
    }

    public void DFS(Node seed, Map<Node, Node> oldToNew) {
        Node copy = oldToNew.get(seed);
        for (Node nei : seed.neighbors) {
            Node copyNei = oldToNew.getOrDefault(nei, null);
            if (copyNei == null) {
                copyNei = new Node(nei.val);
                oldToNew.put(nei, copyNei);
                DFS(nei, oldToNew);
            }
            copy.neighbors.add(copyNei);
        }
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
