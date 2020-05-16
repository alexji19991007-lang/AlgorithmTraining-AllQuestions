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
                    // i + 2 - k = i + 1 - k + 1, look at top right corner, top side
                    // j + 2 - k = j + 1 - k + 1, look at bottom left corner, left side
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
