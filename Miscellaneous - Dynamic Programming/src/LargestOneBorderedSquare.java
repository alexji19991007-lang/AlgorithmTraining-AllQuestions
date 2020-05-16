public class LargestOneBorderedSquare {
    public int largest1BorderedSquare(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] RL = setup_RL(grid, rows, cols);
        int[][] BT = setup_BT(grid, rows, cols);
        int solution = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) {
                    int n = RL[i][j];
                    int depth = rows - i;
                    for (int k = Math.min(n, depth); k > 0; --k) {
                        if (RL[i + k - 1][j] >= k && BT[i][j] >= k && BT[i][j + k - 1] >= k) {
                            solution = Math.max(k, solution);
                        }
                    }
                }
            }
        }
        return solution * solution;
    }

    public int[][] setup_RL(int[][] grid, int rows, int cols) {
        int[][] RL = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            int n = cols - 1;
            RL[i][n] = grid[i][n];
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    RL[i][j] = RL[i][j + 1] + 1;
                } else {
                    RL[i][j] = 0;
                }
            }
        }
        return RL;
    }

    public int[][] setup_BT(int[][] grid, int rows, int cols) {
        int[][] BT = new int[rows][cols];
        for (int i = 0; i < cols; ++i) {
            int n = rows - 1;
            BT[n][i] = grid[n][i];
            for (int j = n - 1; j >= 0; --j) {
                if (grid[j][i] == 1) {
                    BT[j][i] = BT[j + 1][i] + 1;
                } else {
                    BT[j][i] = 0;
                }
            }
        }
        return BT;
    }
}
