import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestSumFromTwoSortedArrays {
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

    public int kthSum(int[] A, int[] B, int k) {
        Queue<Cell> minHeap = new PriorityQueue<>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                if (c1.value == c2.value) {
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });
        int rows = A.length;
        int cols = B.length;
        boolean[][] visited = new boolean[rows][cols];
        minHeap.offer(new Cell(0, 0, A[0] + B[0]));
        visited[0][0] = true;
        int[][] direction = new int[][] {{0, 1}, {1, 0}};
        for (int i = 0; i < k - 1; ++i) {
            Cell cur = minHeap.poll();
            for (int[] dir : direction) {
                int neighborX = cur.row + dir[0];
                int neighborY = cur.col + dir[1];
                if (neighborX >= 0 && neighborX < rows && neighborY >= 0 && neighborY < cols
                        && !visited[neighborX][neighborY]) {
                    visited[neighborX][neighborY] = true;
                    minHeap.offer(new Cell(neighborX, neighborY, A[neighborX] + B[neighborY]));
                }
            }
        }
        return minHeap.peek().value;
    }
}
// Time: O(klogk) since the heap is at most size k;
// Space: O(rows * cols);
