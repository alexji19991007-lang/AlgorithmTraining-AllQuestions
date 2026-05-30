// 1. What does dp[i][j] mean?
// We do NOT use a single dp here.
// Instead, we use two helper arrays:
//
// left[i][j]: number of consecutive 1s ending at (i, j) from the left
// up[i][j]: number of consecutive 1s ending at (i, j) from the top
//
// These help us quickly check whether a square border is valid.

// 2. What is the base case?
// First row: up[0][j] = matrix[0][j]
// First column: left[i][0] = matrix[i][0]
// When matrix[i][j] == 1:
//   left[i][j] = left[i][j - 1] + 1
//   up[i][j] = up[i - 1][j] + 1
//
// When matrix[i][j] == 0:
//   left[i][j] = 0
//   up[i][j] = 0

// 3. What is the recurrence / logic?
// For each cell (i, j), treat it as the bottom-right corner of a square.
//
// Step 1:
// The maximum possible side length is:
//   maxLen = min(left[i][j], up[i][j])
// (bottom edge and right edge must be valid)
//
// Step 2:
// Try all possible square sizes k from maxLen down to 1:
//   For a square of size k:
//
//   Check top edge:
//     left[i - k + 1][j] >= k
//
//   Check left edge:
//     up[i][j - k + 1] >= k
//
//   If both conditions are satisfied:
//     we found a valid square of size k
//
// Step 3:
// Take the maximum k over all positions

public class LargestSquareSurroundedByOne {
    public int largestSquareSurroundedByOne(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        int M = matrix.length;
        int N = matrix[0].length;
        // Use M + 1 & N + 1 to avoid handling multiple edge cases:
        // The info of matrix[i][j] is mapped to left[i + 1][j + 1] and up[i + 1][j + 1]
        int[][] left = new int[M + 1][N + 1]; // Left to Right
        int[][] up = new int[M + 1][N + 1]; // Up to Down
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                // Avoid edge cases here
                if (matrix[i][j] == 1) {
                    left[i + 1][j + 1] = left[i + 1][j] + 1;
                    up[i + 1][j + 1] = up[i][j + 1] + 1;
                }
                // Take matrix[i][j] as the bot-right corner of the square we are looking at.
                // The maximum length of its sides is equal to the min between
                // left[i + 1][j + 1] & up[i + 1][j + 1] (min value between bottom side and right side).
                int maxLen = Math.min(left[i + 1][j + 1], up[i + 1][j + 1]);
                // Then we have to check the top side and left side.
                for (int k = maxLen; k >= 1; k--) {
                    // Make sure these two sides are greater than or equal to our current maxLen
                    // i + 2 - k = (i + 1) - k + 1, look at top right corner, top side
                    // j + 2 - k = (j + 1) - k + 1, look at bottom left corner, left side
                    if (left[i + 2 - k][j + 1] >= k && up[i + 1][j + 2 - k] >= k) {
                        res = Math.max(res, k);
                        break;
                    }
                }
            }
        }
        return res;
    }
}
