public class LargestXOf1s {
    public int largest(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] leftUp = leftUp(matrix, matrix.length, matrix[0].length);
        int[][] rightDown = rightDown(matrix, matrix.length, matrix[0].length);
        return merge(leftUp, rightDown, matrix.length, matrix[0].length);
    }

    public int merge(int[][] leftUp, int[][] rightDown, int R, int C) {
        int res = 0;
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
                res = Math.max(res, leftUp[i][j]);
            }
        }
        return res;
    }

    public int[][] leftUp(int[][] matrix, int R, int C) {
        // Longest Consecutive 1s (From top-left to bottom-right).
        int[][] left = new int[R][C];
        // Longest Consecutive 1s (From top-right to bottom-left).
        int[][] up = new int[R][C];
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (matrix[i][j] == 1) {
                    // 看每个格子的左上角格子
                    left[i][j] = getNumber(left, i - 1, j - 1, R, C) + 1;
                    // 看每个格子的右上角格子
                    up[i][j] = getNumber(up, i - 1, j + 1, R, C) + 1;
                }
            }
        }
        // 将left和up结合，left[i][j] = max(left[i][j], up[i][j])，最终结果存到left中并返回
        merge(left, up, R, C);
        return left;
    }

    public int[][] rightDown(int[][] matrix, int R, int C) {
        // Longest Consecutive 1s (From bottom-right to top-left).
        int[][] right = new int[R][C];
        // Longest Consecutive 1s (From bottom-left to top-right).
        int[][] down = new int[R][C];
        for (int i = R - 1; i >= 0; --i) {
            for (int j = C - 1; j >= 0; --j) {
                if (matrix[i][j] == 1) {
                    // 看每个格子的右下角格子
                    right[i][j] = getNumber(right, i + 1, j + 1, R, C) + 1;
                    // 看每个格子的左下角格子
                    down[i][j] = getNumber(down, i + 1, j - 1, R, C) + 1;
                }
            }
        }
        // 将right和down结合，right[i][j] = max(right[i][j], down[i][j])，最终结果存到right中并返回
        merge(right, down, R, C);
        return right;
    }

    public int getNumber(int[][] number, int x, int y, int R, int C) {
        if (x < 0 || x >= R || y < 0 || y >= C) {
            return 0;
        }
        return number[x][y];
    }
}
