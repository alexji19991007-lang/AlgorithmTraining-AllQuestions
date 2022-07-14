import java.util.*;

// LeetCode 1376
public class TimeNeededToInformAllEmployees {
    static class Employee {
        int id;
        int cumulativeTime;

        public Employee(int id, int cumulativeTime) {
            this.id = id;
            this.cumulativeTime = cumulativeTime;
        }
    }

    // BFS Solution
    // TC: O(n)
    // SC: O(n)
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> managerToSub = new HashMap<>();
        Queue<Employee> queue = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < manager.length; ++i) {
            managerToSub.putIfAbsent(manager[i], new ArrayList<>());
            managerToSub.get(manager[i]).add(i);
        }

        queue.offer(new Employee(headID, 0));
        while (!queue.isEmpty()) {
            Employee curEmployee = queue.poll();
            res = Math.max(res, curEmployee.cumulativeTime);
            // If the current employee has subordinates
            if (managerToSub.containsKey(curEmployee.id)) {
                List<Integer> subordinatesId = managerToSub.get(curEmployee.id);
                for (int id : subordinatesId) {
                    queue.offer(new Employee(id, curEmployee.cumulativeTime + informTime[curEmployee.id]));
                }
            }
        }
        return res;
    }

    // DFS Solution (which actually executes faster)
    // TC: O(n)
    // SC: O(n)
    public int numOfMinutesDFS(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> managerToSub = new HashMap<>();

        for (int i = 0; i < manager.length; ++i) {
            managerToSub.putIfAbsent(manager[i], new ArrayList<>());
            managerToSub.get(manager[i]).add(i);
        }

        return dfs(managerToSub, headID, informTime);
    }

    public int dfs(Map<Integer, List<Integer>> managerToSub, int curEmployeeId, int[] informTime) {
        // This is the base case, if this employee has no subordinates, then we just return 0 (it does not need to inform anyone)
        if (!managerToSub.containsKey(curEmployeeId)) {
            return 0;
        }
        int res = 0;
        for (int id : managerToSub.get(curEmployeeId)) {
            res = Math.max(res, dfs(managerToSub, id, informTime));
        }
        return res + informTime[curEmployeeId];
    }
}
