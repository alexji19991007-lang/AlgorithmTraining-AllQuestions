public class LongestCrossOf1s {
    public int largest(int[][] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int M = matrix[0].length;
        if (M == 0) {
            return 0;
        }
        int[][] leftUp = leftUp(matrix, N, M);
        int[][] rightDown = rightDown(matrix, N, M);
        return merge(leftUp, rightDown, N, M);
    }

    public int merge(int[][] leftUp, int[][] rightDown, int N, int M) {
        int res = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
                res = Math.max(res, leftUp[i][j]);
            }
        }
        return res;
    }

    public int[][] leftUp(int[][] matrix, int N, int M) {
        int[][] left = new int[N][M]; // 从左往右
        int[][] up = new int[N][M]; // 从上往下
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (matrix[i][j] == 1) {
                    if (i == 0 && j == 0) {
                        up[i][j] = 1;
                        left[i][j] = 1;
                    } else if (i == 0) {
                        up[i][j] = 1;
                        left[i][j] = left[i][j - 1] + 1;
                    } else if (j == 0) {
                        up[i][j] = up[i - 1][j] + 1;
                        left[i][j] = 1;
                    } else {
                        up[i][j] = up[i - 1][j] + 1;
                        left[i][j] = left[i][j - 1] + 1;
                    }
                }
            }
        }
        // merge left and up, return the merged matrix
        merge(left, up, N, M);
        return left;
    }

    public int[][] rightDown(int[][] matrix, int N, int M) {
        int[][] right = new int[N][M]; // 从右往左
        int[][] down = new int[N][M]; // 从下往上
        for (int i = N - 1; i >= 0; --i) {
            for (int j = M - 1; j >= 0; --j) {
                if (matrix[i][j] == 1) {
                    if (i == N - 1 && j == M - 1) {
                        down[i][j] = 1;
                        right[i][j] = 1;
                    } else if (i == N - 1) {
                        down[i][j] = 1;
                        right[i][j] = right[i][j + 1] + 1;
                    } else if (j == M - 1) {
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = 1;
                    } else {
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = right[i][j + 1] + 1;
                    }
                }
            }
        }
        // merge right and down, return the merged matrix
        merge(right, down, N, M);
        return right;
    }
}
