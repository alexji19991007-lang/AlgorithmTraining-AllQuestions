import java.util.*;

public class CourseSchedule2 {
    private static final int WHITE = 1;
    private static final int GRAY = 2;
    private static final int BLACK = 3;

    // boolean variable to detect if there is a cycle
    private boolean isPossible;
    // hash map to see the visit status of a node
    private Map<Integer, Integer> color;
    // hash map to represent the adjacency list
    private Map<Integer, List<Integer>> adjList;
    // stack to represent our topological sort
    private Stack<Integer> topoSort;

    // Do initialization work
    public void init(int numCourses) {
        this.isPossible = true;
        this.color = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topoSort = new Stack<>();

        // All the vertices are initially WHITE
        for (int i = 0; i < numCourses; ++i) {
            this.color.put(i, WHITE);
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Initialization
        this.init(numCourses);
        // Build the adjacency list
        for (int i = 0; i < prerequisites.length; ++i) {
            // In this problem, every course will be viewed as a node in a directed graph. A
            // directed edge will come out from A and go to B, where A is the prerequisite of B.
            int pointFrom = prerequisites[i][1];
            int pointTo = prerequisites[i][0];
            // However, course A may be the prerequisite of many other courses, so we need to build
            // a list to store the courses that have A as their prerequisite (i.e. lstTo).
            List<Integer> lstTo = adjList.getOrDefault(pointFrom, new ArrayList<>());
            lstTo.add(pointTo);
            // Add the current node and its directed nodes into the map to build the adjacency list
            adjList.put(pointFrom, lstTo);
        }
        for (int i = 0; i < numCourses && isPossible; ++i) {
            // Pick a vertex, if it's not visited, start DFS on it
            if (color.get(i) == WHITE) {
                dfs(i);
            }
        }
        int[] order;
        // get the possible order
        if (isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; ++i) {
                order[i] = topoSort.pop();
            }
        } else {
            order = new int[0];
        }
        return order;
    }

    // Do DFS on one node
    private void dfs(int node) {
        // If we already found a cycle, it is impossible to build a schedule, thus immediately return
        if (!isPossible) {
            return;
        }
        // This node is discovered, but not finished
        this.color.put(node, GRAY);
        // Get the adjacency list of the current node
        for (Integer neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            // If this neighbor is not discovered yet, continue DFS on this neighbor
            if (color.get(neighbor) == WHITE) {
                dfs(neighbor);
            }
            // If the neighbor is discovered, but not finished, then we have found a cycle, so no way to find a valid schedule
            else if (color.get(neighbor) == GRAY) {
                isPossible = false;
                return;
            }
            // It is okay if we find a finished node
        }
        // This node is finished, paint it to black
        color.put(node, BLACK);
        // push this node onto our stack
        topoSort.push(node);
    }
}
