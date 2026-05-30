// 1. What does dp[i][j] mean?
// dp[i][j] represents the minimum number of operations (insert, delete, replace)
// needed to transform the first i characters of string "one"
// into the first j characters of string "two".

// 2. What is the base case?
// dp[0][j] = j
// Transforming an empty string into the first j characters of "two"
// requires j insert operations.
//
// dp[i][0] = i
// Transforming the first i characters of "one" into an empty string
// requires i delete operations.

// 3. What is the recurrence relation?
// If one.charAt(i - 1) == two.charAt(j - 1):
//   dp[i][j] = dp[i - 1][j - 1]
//   (no operation needed since characters match)
//
// Otherwise:
//   dp[i][j] = 1 + min(
//       dp[i - 1][j - 1],  // replace
//       dp[i - 1][j],      // delete
//       dp[i][j - 1]       // insert
//   )
//
// Explanation:
// - Replace: change one[i-1] to two[j-1]
// - Delete: remove one[i-1]
// - Insert: add two[j-1] to "one"

public class EditDistance {
    public int editDistance(String one, String two) {
        if (one.isEmpty()) return two.length();
        if (two.isEmpty()) return one.length();
        int w1 = one.length();
        int w2 = two.length();
        // dp[i][j] is the minimum number of actions to transform the substring (the first i letters
        // of s1 to the first j letters of s2)
        int[][] dp = new int[w1 + 1][w2 + 1];
        for (int i = 0; i <= w1; ++i) {
            for (int j = 0; j <= w2; ++j) {
                if (i == 0) {
                    dp[0][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = i;
                    continue;
                }
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 左上角
                    int replace = 1 + dp[i - 1][j - 1];
                    // 上边
                    int delete = 1 + dp[i - 1][j];
                    // 左边
                    int insert = 1 + dp[i][j - 1];
                    dp[i][j] = Math.min(replace, Math.min(delete, insert));
                }
            }
        }
        return dp[w1][w2];
    }

    // Follow Up: What if we want to avoid using n^2 extra space?
    // Use two arrays to solve this problem
    public static int minDistance(String one, String two) {
        if (one.isEmpty()) return two.length();
        if (two.isEmpty()) return one.length();
        // Always make sure one is the shorter one.
        if (one.length() > two.length()) {
            String temp = two;
            two = one;
            one = temp;
        }
        int[] prevRow = new int[one.length() + 1];
        int[] curRow = new int[one.length() + 1];
        for (int i = 0; i < prevRow.length; ++i) {
            prevRow[i] = i;
        }
        for (int i = 1; i <= two.length(); ++i) {
            for (int j = 0; j <= one.length(); ++j) {
                if (j == 0) {
                    curRow[0] = i;
                    continue;
                }
                if (one.charAt(j - 1) == two.charAt(i - 1)) {
                    curRow[j] = prevRow[j - 1];
                } else {
                    int replace = 1 + prevRow[j - 1];
                    int delete = 1 + prevRow[j];
                    int insert = 1 + curRow[j - 1];
                    curRow[j] = Math.min(replace, Math.min(delete, insert));
                }
            }
            // Swap prevRow & curRow so curRow becomes prevRow, and curRow points to a new array
            // (We no longer care about the original elements in the prevRow array)
            int[] temp = prevRow;
            prevRow = curRow;
            curRow = temp;
        }
        return prevRow[one.length()];
    }
}
