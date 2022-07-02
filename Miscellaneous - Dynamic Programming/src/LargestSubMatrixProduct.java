public class LargestSubMatrixProduct {
    public double largest(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            double[] rowProduct = new double[cols];
            // rowProduct[col] shall represents the product of col from row i to row j
            for (int col = 0; col < cols; col++) {
                rowProduct[col] = 1;
            }
            for (int j = i; j < rows; j++) {
                for (int col = 0; col < cols; col++) {
                    rowProduct[col] *= matrix[j][col];
                }
                max = Math.max(max, findMax(rowProduct));
            }
        }
        return max;
    }

    public double findMax(double[] nums) {
        // We have to keep two records because a negative product could become very large if it is
        // multiplied by another negative number.
        double maxBefore = nums[0];
        double minBefore = nums[0];
        double curMax = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            double maxNow = Math.max(Math.max(maxBefore * nums[i], minBefore * nums[i]), nums[i]);
            double minNow = Math.min(Math.min(maxBefore * nums[i], minBefore * nums[i]), nums[i]);
            curMax = Math.max(curMax, maxNow);
            maxBefore = maxNow;
            minBefore = minNow;
        }
        return curMax;
    }


}
