public class LongestCommonSubsequence {
    public int longest(String source, String target) {
        if (source.length() == 0 || target.length() == 0) {
            return 0;
        }
        int m = source.length(), n = target.length();
        // M[i][j] represents the length of the longest common subsequence between a[0...i - 1]
        // and b[0...j - 1]
        int[][] M = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0) {
                    M[0][j] = 0;
                    continue;
                }
                if (j == 0) {
                    M[i][0] = 0;
                    continue;
                }
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    M[i][j] = M[i - 1][j - 1] + 1;
                } else {
                    M[i][j] = Math.max(M[i - 1][j], M[i][j - 1]);
                }
            }
        }
        return M[m][n];
    }
}
