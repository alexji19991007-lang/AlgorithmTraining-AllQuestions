import java.util.ArrayDeque;
import java.util.Queue;

// LeetCode 286
public class WallsAndGates {
    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static final int EMPTY = Integer.MAX_VALUE;
    public static final int WALL = -1;
    public static final int GATE = 0;

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) {
            return;
        }
        int n = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (rooms[r][c] == GATE) {
                    queue.offer(new int[] {r, c});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0], curCol = cur[1];
            for (int[] dir : DIRS) {
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || rooms[newRow][newCol] != EMPTY) {
                    continue;
                }
                rooms[newRow][newCol] = rooms[curRow][curCol] + 1;
                queue.offer(new int[] {newRow, newCol});
            }
        }
    }
}
