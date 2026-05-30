// 1. What does dp[i][j] mean?
// We do NOT use a single dp array.
// Instead, we compute directional DP:
//
// left[i][j]  = number of consecutive 1s ending at (i,j) from left
// up[i][j]    = number of consecutive 1s ending at (i,j) from top
// right[i][j] = number of consecutive 1s ending at (i,j) from right
// down[i][j]  = number of consecutive 1s ending at (i,j) from bottom
//
// After merging:
// leftUp[i][j] = min(left[i][j], up[i][j])
// rightDown[i][j] = min(right[i][j], down[i][j])
//
// Final dp meaning:
// dp[i][j] = min(leftUp[i][j], rightDown[i][j])
// It represents the maximum arm length of a cross centered at (i, j).

// 2. What is the base case?
// If matrix[i][j] == 0:
//   all directional values = 0
//
// If matrix[i][j] == 1:
//   left[i][j]  = (j == 0 ? 0 : left[i][j - 1]) + 1
//   up[i][j]    = (i == 0 ? 0 : up[i - 1][j]) + 1
//   right[i][j] = (j == C - 1 ? 0 : right[i][j + 1]) + 1
//   down[i][j]  = (i == R - 1 ? 0 : down[i + 1][j]) + 1

// 3. What is the recurrence relation?
// For each direction:
//
// left[i][j]  = (matrix[i][j] == 1) ? left[i][j - 1] + 1 : 0
// up[i][j]    = (matrix[i][j] == 1) ? up[i - 1][j] + 1 : 0
// right[i][j] = (matrix[i][j] == 1) ? right[i][j + 1] + 1 : 0
// down[i][j]  = (matrix[i][j] == 1) ? down[i + 1][j] + 1 : 0
//
// Then combine:
//
// dp[i][j] = min(
//     left[i][j],
//     up[i][j],
//     right[i][j],
//     down[i][j]
// )
//
// The result is the maximum dp[i][j] over all cells.

// | 题目                      | 本质          |
// | -----------------------  | ----------- |
// | Largest Square of 1s     | 看 block（内部） |
// | Square Surrounded by 1s  | 看边          |
// | Largest Cross of 1s      | 看四个方向       |

public class LongestCrossOf1s {
    public int largest(int[][] matrix) {
        int R = matrix.length;
        if (R == 0) {
            return 0;
        }
        int C = matrix[0].length;
        if (C == 0) {
            return 0;
        }
        int[][] leftUp = leftUp(matrix, R, C);
        int[][] rightDown = rightDown(matrix, R, C);
        return merge(leftUp, rightDown, R, C);
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
        int[][] left = new int[R][C]; // 从左往右
        int[][] up = new int[R][C]; // 从上往下
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (matrix[i][j] == 1) {
                    left[i][j] = getNumber(left, i, j - 1, R, C) + 1;
                    up[i][j] = getNumber(up, i - 1, j, R, C) + 1;
                }
            }
        }
        // merge left and up, return the merged matrix
        merge(left, up, R, C);
        return left;
    }

    public int[][] rightDown(int[][] matrix, int R, int C) {
        int[][] right = new int[R][C]; // 从右往左
        int[][] down = new int[R][C]; // 从下往上
        for (int i = R - 1; i >= 0; --i) {
            for (int j = C - 1; j >= 0; --j) {
                if (matrix[i][j] == 1) {
                    right[i][j] = getNumber(right, i, j + 1, R, C) + 1;
                    down[i][j] = getNumber(down, i + 1, j, R, C) + 1;
                }
            }
        }
        // merge right and down, return the merged matrix
        merge(right, down, R, C);
        return right;
    }

    private int getNumber(int[][] matrix, int x, int y, int R, int C) {
        if (x < 0 || x >= R || y < 0 || y >= C) {
            return 0;
        }
        return matrix[x][y];
    }
}
