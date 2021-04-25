import java.util.*;

public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // graph[i]: a list of courses that take course i as their prerequisite
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] dependency : prerequisites) {
            // pre is the prerequisite of next
            int next = dependency[0];
            int pre = dependency[1];
            graph.get(pre).add(next);
        }
        return topologicalSort(graph);
    }

    public int[] topologicalSort(List<List<Integer>> graph) {
        int numCourses = graph.size();
        int[] topologicalOrder = new int[numCourses];
        int[] incomingEdges = new int[numCourses];
        for (List<Integer> integers : graph) {
            for (int y : integers) {
                incomingEdges[y]++;
            }
        }
        // Courses in the queue are courses whose prerequisites have all been satisfied.
        Queue<Integer> q = new ArrayDeque<>();
        for (int x = 0; x < numCourses; ++x) {
            // incoming edges = 0 --> this course has no prerequisites
            if (incomingEdges[x] == 0) {
                q.offer(x);
            }
        }
        int numExpanded = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            topologicalOrder[numExpanded++] = x;
            // for all courses y that take course x as their prerequisite
            for (int y : graph.get(x)) {
                // incomingEdges[y]-- --> if now incomingEdges[y] == 0, that means we have satisfied
                // all prerequisites of course y, so we can take y now. Thus, add y to the queue.
                if (--incomingEdges[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return numExpanded == numCourses ? topologicalOrder : new int[]{};
    }
}
