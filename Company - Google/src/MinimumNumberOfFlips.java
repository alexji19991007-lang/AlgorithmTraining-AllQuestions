import java.util.*;

// LeetCode 1284
public class MinimumNumberOfFlips {
    // TC: O(2^(mn)), but it's bounded by O((mn)^k) where k is the actual number of steps to get to the final state
    // SC: O(2^(mn))
    public int minFlips(int[][] mat) {
        Config start = new Config(mat, 0);
        if (start.isFinalMatrix()) {
            return 0;
        }
        Set<Integer> visitedState = new HashSet<>();
        visitedState.add(start.toBinaryForm());
        Queue<Config> queue = new ArrayDeque<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Config cur = queue.poll();
            for (Config nei : cur.getNeighbors()) {
                int neighborState = nei.toBinaryForm();
                if (!visitedState.contains(neighborState)) {
                    if (nei.isFinalMatrix()) {
                        return nei.step;
                    }
                    visitedState.add(neighborState);
                    queue.offer(nei);
                }
            }
        }
        return -1;
    }

    static class Config {
        // we can even change the matrix to binary form to reduce more time
        int[][] matrix;
        int rows;
        int cols;
        int step;

        public Config(int[][] matrix, int step) {
            this.matrix = matrix;
            this.rows = matrix.length;
            this.cols = matrix[0].length;
            this.step = step;
        }

        public boolean isFinalMatrix() {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (matrix[i][j] != 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        public List<Config> getNeighbors() {
            List<Config> neighbors = new ArrayList<>();
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    neighbors.add(flip(i, j));
                }
            }
            return neighbors;
        }

        public Config flip(int row, int col) {
            int[][] nextMatrix = new int[rows][cols];
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (isNeighborOrSelf(i, j, row, col)) {
                        nextMatrix[i][j] = 1 - matrix[i][j];
                    } else {
                        nextMatrix[i][j] = matrix[i][j];
                    }
                }
            }
            return new Config(nextMatrix, step + 1);
        }

        public boolean isNeighborOrSelf(int i, int j, int row, int col) {
            return (i == row && j == col) || (i == row && j == col + 1) || (i == row + 1 && j == col) || (i == row && j == col - 1) || (i == row - 1 && j == col);
        }

        public int toBinaryForm() {
            int binaryForm = 0;
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    binaryForm += (matrix[i][j] << (i * rows + j));
                }
            }
            return binaryForm;
        }
    }
}
