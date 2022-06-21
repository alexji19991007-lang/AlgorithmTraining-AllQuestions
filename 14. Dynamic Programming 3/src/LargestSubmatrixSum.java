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
