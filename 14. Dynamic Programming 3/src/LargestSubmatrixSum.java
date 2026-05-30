// 1. What does dp[i] mean?
// In the 1D helper (max function):
// dp[k] represents the maximum subarray sum ending at index k
// (same as Kadane’s algorithm)
//
// In the 2D context:
// cur[c] represents the sum of elements in column c
// between row i and row j (inclusive)

// 2. What is the base case?
// For Kadane (1D):
// dp[0] = cur[0]
//
// For 2D:
// For each new starting row i:
//   cur[] is initialized to all 0s
//   (represents empty accumulation before adding rows)

// 3. What is the recurrence relation?
// Step 1: Collapse rows
// For each pair of rows (i, j):
//   cur[c] += matrix[j][c]
// This builds a 1D array representing column sums between rows i and j
//
// Step 2: Apply Kadane on cur[]
// dp[k] = max(dp[k-1] + cur[k], cur[k])
//
// Step 3:
// Take the global maximum over all row pairs

public class LargestSubmatrixSum {
    public int largest(int[][] matrix) {
        int R = matrix.length, C = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < R; ++i) {
            int[] cur = new int[C];
            for (int j = i; j < R; ++j) {
                // Do prefix sum for rows
                add(cur, matrix[j]);
                // Update result
                res = Math.max(res, max(cur));
            }
        }
        return res;
    }

    public void add(int[] cur, int[] toAdd) {
        // Add each column with previous sum of that column
        for (int i = 0; i < cur.length; ++i) {
            cur[i] += toAdd[i];
        }
    }

    public int max(int[] cur) {
        int res = cur[0];
        int temp = cur[0];
        for (int i = 1; i < cur.length; ++i) {
            temp = Math.max(temp + cur[i], cur[i]);
            res = Math.max(res, temp);
        }
        return res;
    }
}
