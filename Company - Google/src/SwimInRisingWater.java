import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

// LeetCode 778
public class SwimInRisingWater {
    // This problem is essentially asking us to find a path whose maximum value is as small as possible.
    public static final int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // TC: O(n^2 * log(n)), we will expand atmost n^2 nodes and for each node we need log(n) time to poll() from heap and offer() to heap
    // SC: O(n^2), which is the maximum size of the heap
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> minHeap = new PriorityQueue<>((c1, c2) -> {
            int cellOne = grid[c1 / n][c1 % n];
            int cellTwo = grid[c2 / n][c2 % n];
            if (cellOne == cellTwo) {
                return 0;
            }
            return cellOne < cellTwo ? -1 : 1;
        });
        minHeap.offer(0);
        int res = 0;
        visited.add(0);
        while (!minHeap.isEmpty()) {
            int cell = minHeap.poll();
            int row = cell / n, col = cell % n;
            res = Math.max(res, grid[row][col]);
            if (row == n - 1 && col == n - 1) {
                return res;
            }
            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                int newCell = newRow * n + newCol;
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !visited.contains(newCell)) {
                    minHeap.offer(newCell);
                    visited.add(newCell);
                }
            }
        }
        return -1;
    }
}
