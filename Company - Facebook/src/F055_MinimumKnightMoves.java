import java.util.*;

// LeetCode 1197
public class F055_MinimumKnightMoves {
    private final int[][] DIRS = new int[][] {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

    // Method 1: BFS
    // TC: O(abs(x) * abs(y))
    // SC: O(abs(x) * abs(y))
    public int minKnightMoves(int x, int y) {
        // Only look at the first quadrant
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        int numSteps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();
                int curX = cur[0], curY = cur[1];
                if (curX == x && curY == y) {
                    return numSteps;
                }
                for (int[] dir : DIRS) {
                    int newX = curX + dir[0];
                    int newY = curY + dir[1];
                    // newX >= -1 && newY >= -1 because of some special cases:
                    // For example, to reach (1,1) from (0, 0), the best way is to get (2, -1) or
                    // (-1, 2) first, then (1,1) (two steps). If we eliminate all coordinates with
                    // negative numbers, then we can't reach (1,1) from (0, 0) within two steps.
                    //
                    // The reason to set -1 as the lower bound is that if we instead set -2,
                    // from (-2, -2) to any points in the first quadrant requires no fewer than two
                    // moves.
                    if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            numSteps++;
        }
        return -1;
    }

    // Method 2: DP (Walking Backwards)
    public int minKnightMoves_DP(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Map<String, Integer> memo = new HashMap<>();
        return helper(x, y, memo);
    }

    private int helper(int x, int y, Map<String, Integer> memo) {
        String key = x + ":" + y;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // Special cases (0, 0), (1, 1), (2, 0), (0, 2)
        if (x + y == 0) {
            return 0;
        } else if (x + y == 2) {
            return 2;
        }
        int min = Math.min(helper(Math.abs(x - 1), Math.abs(y - 2), memo),
                helper(Math.abs(x - 2), Math.abs(y - 1), memo)) + 1;
        memo.put(key, min);
        return min;
    }
}
