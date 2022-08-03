package HackerRank;

import java.util.ArrayDeque;
import java.util.Queue;

public class KnightsPath {
    public static void main(String[] args) {
        KnightsPath test = new KnightsPath();
        System.out.println(test.minimumSteps(8, 4, 2, 2, 6, 2, 4));
    }

    public static final int[][] DIRS = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    public int minimumSteps(int n, int startRow, int startCol, int endRow, int endCol, int bishopRow, int bishopCol) {
        int steps = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startRow, startCol});
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();
                for (int[] dir : DIRS) {
                    int newRow = cur[0] + dir[0];
                    int newCol = cur[1] + dir[1];
                    if (newRow == endRow && newCol == endCol) {
                        return steps;
                    }
                    if (isValidMove(n, newRow, newCol, bishopRow, bishopCol)) {
                        queue.offer(new int[] {newRow, newCol});
                    }
                }
            }
        }
        return -1;
    }

    private boolean isValidMove(int n, int r, int c, int bR, int bC) {
        return r >= 0 && r < n && c >= 0 && c < n && checkBishop(r, c, bR, bC);
    }

    private boolean checkBishop(int r, int c, int bR, int bC) {
        return (r + c != bR + bC && c - r != bC - bR) || (r == bR && c == bC);
    }
}
