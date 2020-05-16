public class LargestSquareOfMatches {
    public int largestSquareOfMatches(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        int R = matrix.length;
        int C = matrix[0].length;
        int[][] right = new int[R + 1][C + 1];
        int[][] down = new int[R + 1][C + 1];
        for (int i = R - 1; i >= 0; --i) {
            for (int j = C - 1; j >= 0; --j) {
                if (hasRight(matrix[i][j])) {
                    right[i][j] = right[i][j + 1] + 1;
                }
                if (hasDown(matrix[i][j])) {
                    down[i][j] = down[i + 1][j] + 1;
                }
                if (hasBoth(matrix[i][j])) {
                    int maxLen = Math.min(right[i][j], down[i][j]);
                    for (int k = maxLen; k >= 1; --k) {
                        if (right[i + k][j] >= k && down[i][j + k] >= k) {
                            res = Math.max(res, k);
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    public boolean hasRight(int value) {
        return value == 1 || value == 3;
    }

    public boolean hasDown(int value) {
        return value == 2 || value == 3;
    }

    public boolean hasBoth(int value) {
        return value == 3;
    }
}
