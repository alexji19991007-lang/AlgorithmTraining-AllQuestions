import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// LeetCode 785
public class F034_IsGraphBipartite {
    // TC: O(V + E)
    // SC: O(V), a map to store the nodes and their corresponding groups
    public boolean isBipartite(int[][] graph) {
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < graph.length; ++i) {
            if (!BFS(i, visited, graph)) {
                return false;
            }
        }
        return true;
    }

    public boolean BFS(int nodeNumber, Map<Integer, Integer> visited, int[][] graph) {
        if (visited.containsKey(nodeNumber)) {
            return true;
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(nodeNumber);
        visited.put(nodeNumber, 0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            int curGroup = visited.get(cur);
            int neighborGroup = curGroup == 0 ? 1 : 0;
            for (int neighbor : graph[cur]) {
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
