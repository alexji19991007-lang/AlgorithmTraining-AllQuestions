public class LongestIncreasingPathInAMatrix {
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(longestIncreasingPath(matrix));
    }

    // Solve this problem using DFS + Memoization
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        // memo[i][j] means the longest increasing path we can get if we start from the position (i, j)
        int[][] memo = new int[m][n];
        int ans = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    private static int dfs(int[][] matrix, int i, int j, int[][] memo) {
        // if we already know how far we can go from this point, no need to search further,
        // just return the result by reading from the memo
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        for (int[] d : dirs) {
            // Check its four neighbors
            int x = i + d[0], y = j + d[1];
            // if the target number is inbound and is greater than our current value
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
                // do depth first search on it.
                memo[i][j] = Math.max(memo[i][j], dfs(matrix, x, y, memo));
            }
        }
        // because the current value itself has length 1, increment the count for this number by 1
        memo[i][j]++;
        return memo[i][j];
    }
}
