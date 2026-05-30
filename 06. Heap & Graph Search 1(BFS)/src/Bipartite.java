import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;


// High-level idea:
// Suppose we have a graph with node n_1, n_2, n_3, ...., n_p, if this graph is bipartite,
// then we can split the nodes into two groups: group_0 and group_1. For all nodes in group_0,
// their neighbor nodes should be in group_1 and vice versa.
// 准备工作：我们需要知道哪些node我们已经visited过了以及这些node所对应的group --> HashMap<GraphNode, Integer> visited
//
// 1. for every node that are not visited yet 我们把它放到group 0里面。然后我们看这个node所有的neighbor。
//    1.1 如果这个neighbor已经visited过了：
//        - 如果neighbor在group_1里面，没问题，继续看下一个neighbor
//        - 如果neighbor在group_0里面，那就违反了bipartite的定义，return false
//    1.2 如果这个neighbor还没被visited过：
//        - 加到visited HashMap里，给他tag为group_1
// 2. 所有neighbor都visit完了还没有发生冲突，继续看下一个not visited node
// 3. 如果所有node都看完了还没有发生冲突，说明graph是bipartite的。
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
            // Note here, it should be cur's neighbors, not node's neighbors
            for (GraphNode neighbor : cur.neighbors) {
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
