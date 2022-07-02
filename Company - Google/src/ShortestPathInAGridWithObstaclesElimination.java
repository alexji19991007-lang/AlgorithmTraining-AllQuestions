import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// LeetCode 1293
public class ShortestPathInAGridWithObstaclesElimination {
    public static void main(String[] args) {
        ShortestPathInAGridWithObstaclesElimination test = new ShortestPathInAGridWithObstaclesElimination();
        int[][] array = {{0, 0, 0}, {0, 1, 1}, {0, 0, 0}};
        System.out.println(test.shortestPath(array, 1));
    }

    // TC: O(N * K)
    // SC: O(N * K)
    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class MoveState {
        int row;
        int col;
        int k;

        public MoveState(int row, int col, int k) {
            this.row = row;
            this.col = col;
            this.k = k;
        }

        @Override
        public int hashCode() {
            return (this.row + 1) * (this.col + 1) * this.k;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof MoveState) {
                MoveState other = (MoveState) o;
                return this.row == other.row && this.col == other.col && this.k == other.k;
            }
            return false;
        }
    }

    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        if (k >= rows + cols - 2) {
            return rows + cols - 2;
        }
        Queue<MoveState> queue = new ArrayDeque<>();
        Set<MoveState> visitedState = new HashSet<>();
        MoveState start = new MoveState(0, 0, k);
        queue.offer(start);
        visitedState.add(start);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                MoveState cur = queue.poll();
                if (cur.row == rows - 1 && cur.col == cols - 1) {
                    return steps;
                }
                for (int[] dir : DIRS) {
                    int nextRow = cur.row + dir[0];
                    int nextCol = cur.col + dir[1];
                    if (!inBound(nextRow, nextCol, rows, cols)) {
                        continue;
                    }
                    int nextK = cur.k - grid[nextRow][nextCol];
                    MoveState nextState = new MoveState(nextRow, nextCol, nextK);
                    if (nextK >= 0 && !visitedState.contains(nextState)) {
                        visitedState.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public boolean inBound(int r, int c, int rows, int cols) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}
