import java.util.LinkedList;
import java.util.Queue;

// LeetCode 994
public class A005_RottingOranges {
    // TC: O(m * n)
    // SC: O(m * n)
    public static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int numFresh = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    numFresh++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int minutes = 0;
        while (!queue.isEmpty() && numFresh > 0) {
            int size = queue.size();
            minutes++;
            for (int i = 0; i < size && numFresh > 0; ++i) {
                int[] pos = queue.poll();
                for (int[] dir : DIRS) {
                    int newRow = pos[0] + dir[0];
                    int newCol = pos[1] + dir[1];
                    if (inBound(newRow, newCol, n, m) && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        numFresh--;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }
        return numFresh == 0 ? minutes : -1;
    }

    public boolean inBound(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
