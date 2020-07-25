import java.util.Arrays;

// LeetCode 689
public class F031_MaximumSumOf3NonOverlappingSubarrays {
    public static void main(String[] args) {
        int[] nums = new int[] {2, 0, 2, 1, 1, 6, 1, 9, 3, 2};
        F031_MaximumSumOf3NonOverlappingSubarrays test = new F031_MaximumSumOf3NonOverlappingSubarrays();
        System.out.println(Arrays.toString(test.maxSumOfThreeSubarrays(nums, 2)));
    }

    // TC: O(n)
    // SC: O(n)
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length, maxSum = 0;
        int[] sum = new int[n + 1], posLeft = new int[n], posRight = new int[n], ans = new int[3];
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + nums[i]; // do prefix sum
        }
        // Middle array's possible start position is from index k to n - 2 * k
        // Left array's possible starting position is from index 0 to n - 3 * k
        // Right array's possible starting position is from index 2 * k to n - k
        // First determine the starting position of middle array. Then check left and right.
        int total = sum[k] - sum[0];
        // posLeft[i] = Suppose the left array ends at index i or before i, what is the starting
        //              position of the left array such that the sum of the left array is as big as
        //              possible
        for (int i = k; i < n - 2 * k; ++i) {
            int curTotal = sum[i + 1] - sum[i - k + 1];
            if (curTotal > total) {
                posLeft[i] = i - k + 1;
                total = curTotal;
            } else {
                posLeft[i] = posLeft[i - 1];
            }
        }
        // posRight[i] = Suppose the right array starts at index i or after i, what is the starting
        //               position of the right array such that the sum of the right array is as big as
        //               possible
        posRight[n - k] = n - k;
        total = sum[n] - sum[n - k];
        for (int i = n - k - 1; i >= 2 * k; --i) {
            int curTotal = sum[i + k] - sum[i];
            if (curTotal >= total) {
                posRight[i] = i;
                total = curTotal;
            } else {
                posRight[i] = posRight[i + 1];
            }
        }
        // Suppose the middle array starts at index i, calculate the larges possible sum
        for (int i = k; i <= n - 2 * k; ++i) {
            int l = posLeft[i - 1], r = posRight[i + k];
            int curSum = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
            if (curSum > maxSum) {
                maxSum = curSum;
                ans[0] = l;
                ans[1] = i;
                ans[2] = r;
            }
        }
        return ans;
    }
}
