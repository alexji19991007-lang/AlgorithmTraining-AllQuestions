// LeetCode 1937
public class MaximumNumberOfPointsWithCost {
    // TC: O(M * N)
    // SC: O(N)
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long res = 0;
        long[] pre = new long[n];
        for (int i = 0; i < n; ++i) {
            pre[i] = points[0][i];
        }
        for (int i = 0; i < m - 1; ++i) {
            long[] leftMax = new long[n];
            long[] rightMax = new long[n];
            long[] cur = new long[n];
            leftMax[0] = pre[0];
            rightMax[n - 1] = pre[n - 1];
            for (int j = 1; j < n; ++j) {
                leftMax[j] = Math.max(leftMax[j - 1] - 1, pre[j]);
                rightMax[n - j - 1] = Math.max(rightMax[n - j] - 1, pre[n - j - 1]);
            }
            for (int j = 0; j < n; ++j) {
                cur[j] = points[i + 1][j] + Math.max(leftMax[j], rightMax[j]);
            }
            pre = cur;
        }
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, pre[i]);
        }
        return res;
    }
}
