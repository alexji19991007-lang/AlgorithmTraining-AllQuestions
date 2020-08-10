import java.util.*;

// LeetCode 1192
public class A003_CriticalConnectionsInANetwork {
    public static void main(String[] args) {
        A003_CriticalConnectionsInANetwork test = new A003_CriticalConnectionsInANetwork();
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));
        connections.add(Arrays.asList(3, 4));
        connections.add(Arrays.asList(4, 5));
        connections.add(Arrays.asList(5, 3));
        System.out.println(test.criticalConnections(6, connections).toString());
    }

    // TC: O(V+E)
    // SC: O(n)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] disc = new int[n], low = new int[n];
        // use adjacency list instead of matrix will save some memory, adjmatrix will cause MLE
        List<List<Integer>> graph = new ArrayList<>(n);
        List<List<Integer>> res = new ArrayList<>();
        Arrays.fill(disc, -1); // use disc to track if visited (disc[i] == -1)
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // build graph
        for (List<Integer> connection : connections) {
            int from = connection.get(0), to = connection.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        int[] time = {0};
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, low, disc, graph, res, i, time);
            }
        }
        return res;
    }

    private void dfs(int u, int[] low, int[] disc, List<List<Integer>> graph, List<List<Integer>> res, int pre, int[] time) {
        disc[u] = low[u] = ++time[0]; // discover u
        for (int j = 0; j < graph.get(u).size(); j++) {
            int v = graph.get(u).get(j);
            if (v == pre) {
                continue; // if parent vertex, ignore
            }
            if (disc[v] == -1) { // if not discovered
                dfs(v, low, disc, graph, res, u, time);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                    res.add(Arrays.asList(u, v));
                }
            } else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
