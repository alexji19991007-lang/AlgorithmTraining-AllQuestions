package Codility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 1466
public class ReorderRoutesToMakeAllPathsLeadToCityZero {
    public int minReorder(int[] A, int[] B) {
        Map<Integer, List<Integer>> roads = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            int from = A[i], to = B[i];
            roads.putIfAbsent(from, new ArrayList<>());
            roads.get(from).add(to);
            roads.putIfAbsent(to, new ArrayList<>());
            roads.get(to).add(-from);
        }
        return dfs(roads, new boolean[roads.size()], 0);
    }

    public int dfs(Map<Integer, List<Integer>> roads, boolean[] visited, int from) {
        int numChange = 0;
        visited[from] = true;
        for (int to : roads.get(from)) {
            if (!visited[Math.abs(to)]) {
                numChange += (to > 0 ? 1 : 0) + dfs(roads, visited, Math.abs(to));
            }
        }
        return numChange;
    }
}
