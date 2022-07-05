// LeetCode 528
public class RandomPickWithWeight {
    private int[] prefixSums;
    private int totalSum;

    // TC: O(n)
    // SC: O(n)
    public RandomPickWithWeight(int[] w) {
        this.prefixSums = new int[w.length];
        this.totalSum = 0;
        for (int i = 0; i < w.length; ++i) {
            totalSum += w[i];
            this.prefixSums[i] = totalSum;
        }
    }

    // TC: O(logn)
    // SC: O(1)
    public int pickIndex() {
        double target = this.totalSum * Math.random();
        int left = 0, right = this.prefixSums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (target > this.prefixSums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return target > this.prefixSums[left] ? right : left;
    }
}
