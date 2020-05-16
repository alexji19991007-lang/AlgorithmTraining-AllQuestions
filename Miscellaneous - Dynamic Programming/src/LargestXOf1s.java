public class LargestXOf1s {
    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 1}, {1, 1, 0, 1, 1}, {0, 0, 1, 1, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 1, 1}};
        System.out.println(largest(matrix));
    }

    public static int largest(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] leftUp = leftUp(matrix, matrix.length, matrix[0].length);
        int[][] rightDown = rightDown(matrix, matrix.length, matrix[0].length);
        return merge(leftUp, rightDown, matrix.length, matrix[0].length);
    }

    public static int merge(int[][] leftUp, int[][] rightDown, int R, int C) {
        int res = 0;
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
                res = Math.max(res, leftUp[i][j]);
            }
        }
        return res;
    }

    public static int[][] leftUp(int[][] matrix, int R, int C) {
        int[][] left = new int[R][C];
        int[][] up = new int[R][C];
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (matrix[i][j] == 1) {
                    left[i][j] = getNumber(left, i - 1, j - 1, R, C) + 1;
                    up[i][j] = getNumber(up, i - 1, j + 1, R, C) + 1;
                }
            }
        }
        merge(left, up, R, C);
        return left;
    }

    public static int[][] rightDown(int[][] matrix, int R, int C) {
        int[][] right = new int[R][C];
        int[][] down = new int[R][C];
        for (int i = R - 1; i >= 0; --i) {
            for (int j = C - 1; j >= 0; --j) {
                if (matrix[i][j] == 1) {
                    right[i][j] = getNumber(right, i + 1, j + 1, R, C) + 1;
                    down[i][j] = getNumber(down, i + 1, j - 1, R, C) + 1;
                }
            }
        }
        merge(right, down, R, C);
        return right;
    }

    public static int getNumber(int[][] number, int x, int y, int R, int C) {
        if (x < 0 || x >= R || y < 0 || y >= C) {
            return 0;
        }
        return number[x][y];
    }
}
