public class LongestLineOfConsecutiveOneInMatrix {
    public int longestLine(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int res = 0;
        int[][] dp = new int[mat[0].length][4];
        for (int i = 0; i < mat.length; ++i) {
            int old = 0;
            for (int j = 0 ; j < mat[0].length; ++j) {
                if (mat[i][j] == 1) {
                    dp[j][0] = j > 0 ? dp[j - 1][0] + 1 : 1;
                    dp[j][1] = i > 0 ? dp[j][1] + 1 : 1;
                    int prev = dp[j][2];
                    dp[j][2] = (i > 0 && j > 0) ? old + 1 : 1;
                    old = prev;
                    dp[j][3] = (i > 0 && j < mat[0].length - 1) ? dp[j + 1][3] + 1 : 1;
                    res = Math.max(res, Math.max(Math.max(dp[j][0], dp[j][1]), Math.max(dp[j][2], dp[j][3])));
                } else {
                    old = dp[j][2];
                    dp[j][0] = dp[j][1] = dp[j][2] = dp[j][3] = 0;
                }
            }
        }
        return res;
    }
}
