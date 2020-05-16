import java.util.PriorityQueue;

public class KthSmallestNumberInSortedMatrix {
    static class Cell {
        int row;
        int col;
        int value;

        Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        boolean[][] visited = new boolean[numRows][numCols];
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, (c1, c2) -> {
            if (c1.value == c2.value) {
                return 0;
            }
            return c1.value < c2.value ? -1 : 1;
        });
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        for (int i = 0; i < k - 1; ++i) {
            Cell cur = minHeap.poll();
            if (cur.row + 1 < numRows && !visited[cur.row + 1][cur.col]) {
                minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
                visited[cur.row + 1][cur.col] = true;
            }
            if (cur.col + 1 < numCols && !visited[cur.row][cur.col + 1]) {
                minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
                visited[cur.row][cur.col + 1] = true;
            }
        }
        return minHeap.peek().value;
    }
}
