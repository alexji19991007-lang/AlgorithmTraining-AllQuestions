import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class Bipartite {
    public boolean isBipartite(List<GraphNode> graph) {
        Map<GraphNode, Integer> visited = new HashMap<>();
        for (GraphNode curNode : graph) {
            if (!BFS(curNode, visited)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean BFS(GraphNode node, Map<GraphNode, Integer> visited) {
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> q = new ArrayDeque<>();
        q.offer(node);
        visited.put(node, 0);
        while (!q.isEmpty()) {
            GraphNode cur = q.poll();
            int curGroup = visited.get(cur);
            int neighborGroup = curGroup == 0 ? 1 : 0;
            for (GraphNode neighbor : node.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    q.offer(neighbor);
                    visited.put(neighbor, neighborGroup);
                } else if (visited.get(neighbor) != neighborGroup) {
                    return false;
                }
            }
        }
        return true;
    }
}
