import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxWaterTrapped2 {
    public int maxTrapped(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        if (rows < 3 || cols < 3) {
            return 0;
        }
        Queue<Bar> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[rows][cols];
        processBorder(matrix, visited, minHeap, rows, cols);
        int res = 0;
        while (!minHeap.isEmpty()) {
            Bar cur = minHeap.poll();
            List<Bar> unvisitedNeighbors = getUnvisitedNeighbors(cur, matrix, visited);
            for (Bar nei : unvisitedNeighbors) {
                visited[nei.x][nei.y] = true;
                res += Math.max(0, cur.height - nei.height);
                nei.height = Math.max(cur.height, nei.height);
                minHeap.offer(nei);
            }
        }
        return res;
    }

    public void processBorder(int[][] matrix, boolean[][] visited, Queue<Bar> minHeap, int rows, int cols) {
        for (int i = 0; i < rows; ++i) {
            minHeap.offer(new Bar(i, 0, matrix[i][0]));
            visited[i][0] = true;
            minHeap.offer(new Bar(i, cols - 1, matrix[i][cols - 1]));
            visited[i][cols - 1] = true;
        }
        for (int j = 1; j < cols - 1; ++j) {
            minHeap.offer(new Bar(0, j, matrix[0][j]));
            visited[0][j] = true;
            minHeap.offer(new Bar(rows - 1, j, matrix[rows - 1][j]));
            visited[rows - 1][j] = true;
        }
    }

    public List<Bar> getUnvisitedNeighbors(Bar cur, int[][] matrix, boolean[][] visited) {
        List<Bar> unvisitedNei = new ArrayList<>();
        int curX = cur.x, curY = cur.y;
        if (curX + 1 < matrix.length && !visited[curX + 1][curY]) {
            unvisitedNei.add(new Bar(curX + 1, curY, matrix[curX + 1][curY]));
        }
        if (curX - 1 >= 0 && !visited[curX - 1][curY]) {
            unvisitedNei.add(new Bar(curX - 1, curY, matrix[curX - 1][curY]));
        }
        if (curY + 1 < matrix[0].length && !visited[curX][curY + 1]) {
            unvisitedNei.add(new Bar(curX, curY + 1, matrix[curX][curY + 1]));
        }
        if (curY - 1 >= 0 && !visited[curX][curY - 1]) {
            unvisitedNei.add(new Bar(curX, curY - 1, matrix[curX][curY - 1]));
        }
        return unvisitedNei;
    }

    static class Bar implements Comparable<Bar> {
        int x;
        int y;
        int height;

        Bar(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Bar other) {
            if (this.height == other.height) {
                return 0;
            }
            return this.height < other.height ? -1 : 1;
        }
    }
}
