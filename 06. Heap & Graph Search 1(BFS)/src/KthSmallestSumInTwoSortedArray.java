import java.util.*;

public class KthSmallestSumInTwoSortedArray {
    public static final int[][] DIRS = {{1, 0}, {0, 1}};

    static class Cell {
        int row;
        int col;
        int val;

        Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.val = value;
        }
    }

    public int kthSmallest(int[] A, int[] B, int k) {
        Queue<Cell> minHeap = new PriorityQueue<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                if (c1.val == c2.val) {
                    return 0;
                }
                return c1.val < c2.val ? -1 : 1;
            }
        });
        int rows = A.length, cols = B.length;
        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;
        minHeap.offer(new Cell(0, 0, A[0] + B[0]));
        for (int i = 1; i < k; ++i) {
            Cell cur = minHeap.poll();
            for (int[] dir : DIRS) {
                int newI = cur.row + dir[0], newJ = cur.col + dir[1];
                if (newI < rows && newJ < cols && !visited[newI][newJ]) {
                    minHeap.offer(new Cell(newI, newJ, A[newI] + B[newJ]));
                    visited[newI][newJ] = true;
                }
            }
        }
        return minHeap.peek().val;
    }
}
