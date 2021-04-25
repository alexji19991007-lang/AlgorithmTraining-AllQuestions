import java.util.*;

public class DeepCopyUndirectedGraph {
    // Method 1: Using BFS
    public List<GraphNode> copyBFS(List<GraphNode> graph) {
        List<GraphNode> res = new ArrayList<>();
        Queue<GraphNode> q = new ArrayDeque<>();
        Map<GraphNode, GraphNode> oldToNew = new HashMap<>();
        for (GraphNode n : graph) {
            GraphNode newNode = new GraphNode(n.key);
            res.add(newNode);
            q.offer(n); // We need the old node to do BFS
            oldToNew.put(n, newNode);
        }
        while (!q.isEmpty()) {
            GraphNode oldNode = q.poll();
            for (GraphNode oldNeighborNode : oldNode.neighbors) {
                // 在map中查找newNeighborNode是否已经被创建
                GraphNode newNeighborNode = oldToNew.getOrDefault(oldNeighborNode, null);
                if (newNeighborNode == null) {
                    // 如果还没有被创建，创建新的并放到map中一一对应
                    newNeighborNode = new GraphNode(oldNeighborNode.key);
                    oldToNew.put(oldNeighborNode, newNeighborNode);
                    q.offer(oldNeighborNode);
                }
                oldToNew.get(oldNode).neighbors.add(newNeighborNode);
            }
        }
        return res;
    }

    // Method 2: Using DFS
    public List<GraphNode> copyDFS(List<GraphNode> graph) {
        if (graph == null) {
            return null;
        }
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node, new GraphNode(node.key));
                DFS(node, map);
            }
        }
        return new ArrayList<>(map.values());
    }

    public void DFS(GraphNode seed, Map<GraphNode, GraphNode> map) {
        GraphNode copy = map.get(seed);
        for (GraphNode nei : seed.neighbors) {
            GraphNode copyNei = map.getOrDefault(nei, null);
            // If we have not created the copy of the neighbor, create it and put it into the map
            if (copyNei == null) {
                copyNei = new GraphNode(nei.key);
                map.put(nei, copyNei);
                DFS(nei, map);
            }
            // Guaranteed copyNei != null, add it to the neighbor list of our current copy node
            copy.neighbors.add(copyNei);
        }
    }

    static class GraphNode {
        public int key;
        public List<GraphNode> neighbors;

        public GraphNode(int key) {
            this.key = key;
            this.neighbors = new ArrayList<GraphNode>();
        }
    }
}
