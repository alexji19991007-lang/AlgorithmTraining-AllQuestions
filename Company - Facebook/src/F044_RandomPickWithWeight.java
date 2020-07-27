import java.util.Random;

// LeetCode 528
public class F044_RandomPickWithWeight {
    private int[] prefixSum;
    private int totalSum;
    private Random rand;

    // TC: O(n)
    // SC: O(n)
    public F044_RandomPickWithWeight(int[] w) {
        this.prefixSum = new int[w.length];
        int preSum = 0;
        for (int i = 0; i < prefixSum.length; ++i) {
            preSum += w[i];
            this.prefixSum[i] += preSum;
        }
        this.totalSum = preSum;
        this.rand = new Random();
    }

    // TC: O(logn)
    // SC: O(1)
    public int pickIndex() {
        int randNum = rand.nextInt(totalSum);
        // Find the first element that is greater than randNum, return that index
        int left = 0, right = prefixSum.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (prefixSum[mid] <= randNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (prefixSum[left] > randNum) {
            return left;
        } else if (prefixSum[right] > randNum) {
            return right;
        }
        return -1;
    }
}
